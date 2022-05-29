package com.cookandroid.teamproject1.plan.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentPlanFriendInviteBinding
import com.cookandroid.teamproject1.plan.model.RequestAuthUserData
import com.cookandroid.teamproject1.plan.model.ResponseAuthUserData
import com.cookandroid.teamproject1.plan.model.ResponsePlanUserData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * plan 친구 초대 프래그먼트
 */
class PlanFriendInviteFragment : Fragment() {
    private var mBinding : FragmentPlanFriendInviteBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanFriendInviteBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args : PlanFriendInviteFragmentArgs by navArgs()
        val planId = args.planId

        //친구 초대 프래그먼트에서 x버튼을 클릭했을 때 아무것도 실행되지 않고, 그 전 화면으로 가기 위해 planId 전달
        mBinding?.fragmentPlanFriendXIv?.setOnClickListener{
            val action = PlanFriendInviteFragmentDirections.actionPlanFriendInviteFragmentToPlanViewFragment(planId)
            it.findNavController().navigate(action)
        }

        //친구 검색 editText에서 입력하고 있을 때 버튼 활성화
        mBinding?.fragmentPlanFriendSearchEt?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(mBinding?.fragmentPlanFriendSearchEt?.text.toString() != "") {
                    mBinding?.fragmentPlanFriendSearchBt?.setBackgroundResource(R.drawable.confirm_repetition_btn_background_clicked)
                    mBinding?.fragmentPlanFriendSearchBt?.setTextColor(Color.WHITE)
                    mBinding?.fragmentPlanFriendSearchBt?.isEnabled = true
                }
                else {
                    mBinding?.fragmentPlanFriendSearchBt?.setBackgroundResource(R.drawable.confirm_repetition_btn_background)
                    mBinding?.fragmentPlanFriendSearchBt?.setTextColor(Color.parseColor("#3F3F45"))
                    mBinding?.fragmentPlanFriendSearchBt?.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        /**
         * 0514 사용자 검색 조회 api 연동
         * 작성자 : 윤성식
         */
        mBinding?.fragmentPlanFriendSearchBt?.setOnClickListener{
            val call: Call<ResponsePlanUserData> = ServiceCreator.planService.getPlanUser(
                TloverApplication.prefs.getString("jwt", "null"),
                TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                mBinding?.fragmentPlanFriendSearchEt?.text.toString()
            )

            call.enqueue(object: Callback<ResponsePlanUserData>{
                override fun onResponse(
                    call: Call<ResponsePlanUserData>,
                    response: Response<ResponsePlanUserData>
                ) {
                    if(response.code() == 200){
                        Log.e("reponse", "200!!~~~!!")
                        //조회 성공은 했지만 본인 닉네임을 입력했을 경우
                        if(TloverApplication.prefs.getString("userNickname", "null") == mBinding?.fragmentPlanFriendSearchEt?.text.toString()){
                            mBinding?.fragmentPlanFriendIcIv?.visibility = View.GONE
                            mBinding?.fragmentPlanFriendIdTv?.visibility = View.GONE
                            mBinding?.fragmentPlanFriendFinishBt?.setBackgroundResource(R.drawable.certification_requ)
                            mBinding?.fragmentPlanFriendFinishBt?.setTextColor(Color.parseColor("#6E6E76"))
                            mBinding?.fragmentPlanFriendFinishBt?.isEnabled = false
                            Toast.makeText(requireActivity(), "본인 닉네임 입니다.", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            mBinding?.planGetUser = response.body()?.data
                            mBinding?.fragmentPlanFriendIcIv?.visibility = View.VISIBLE
                            mBinding?.fragmentPlanFriendIdTv?.visibility = View.VISIBLE
                            mBinding?.fragmentPlanFriendFinishBt?.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
                            mBinding?.fragmentPlanFriendFinishBt?.setTextColor(Color.WHITE)
                            mBinding?.fragmentPlanFriendFinishBt?.isEnabled = true
                        }
                    }
                    else if(response.code() == 404){
                        mBinding?.fragmentPlanFriendIcIv?.visibility = View.GONE
                        mBinding?.fragmentPlanFriendIdTv?.visibility = View.GONE
                        mBinding?.fragmentPlanFriendFinishBt?.setBackgroundResource(R.drawable.certification_requ)
                        mBinding?.fragmentPlanFriendFinishBt?.setTextColor(Color.parseColor("#6E6E76"))
                        mBinding?.fragmentPlanFriendFinishBt?.isEnabled = false
                        Toast.makeText(requireActivity(), "유저닉네임을 다시 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePlanUserData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        /**
         * 0514 사용자 권한 요청 api 연결
         * 작성자 : 윤성식
         */
        // 권한 요청하는 곳 돌아가면서
        mBinding?.fragmentPlanFriendFinishBt?.setOnClickListener{
            val requestAuthUserData = RequestAuthUserData(
                userNickname = mBinding?.fragmentPlanFriendSearchEt?.text.toString()
            )

            val call: Call<ResponseAuthUserData> = ServiceCreator.planService.postAuthUser(
                TloverApplication.prefs.getString("jwt", "null"),
                TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                planId.toInt(),
                requestAuthUserData
            )

            call.enqueue(object: Callback<ResponseAuthUserData>{
                override fun onResponse(
                    call: Call<ResponseAuthUserData>,
                    response: Response<ResponseAuthUserData>
                ) {
                    if(response.code()==200){
                        Toast.makeText(requireActivity(), "권한 승인을 요청했습니다.", Toast.LENGTH_SHORT).show()
                        //다시 해당 플랜 프래그먼트로 이동
//                        val action = PlanFriendInviteFragmentDirections.actionPlanFriendInviteFragmentToPlanViewFragment(planId)
//                        it.findNavController().navigate(action)
                    }
                    else if(response.code()==400){
                        Toast.makeText(requireActivity(), "해당 유저에게 이미 계획 공유 요청중입니다.", Toast.LENGTH_SHORT).show()
                    }
                    else if(response.code()==403){
                        Toast.makeText(requireActivity(),"이미 공유된 유저입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseAuthUserData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


    }
}