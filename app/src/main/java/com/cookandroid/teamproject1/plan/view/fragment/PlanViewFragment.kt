package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.databinding.FragmentPlanViewBinding
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel.Companion.TAG
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.plan.model.ResponsePlanWriteData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * plan 상세보기 프래그먼트
 */
class PlanViewFragment : Fragment(){
    private var mBinding : FragmentPlanViewBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanViewBinding.inflate(inflater, container, false)
        mBinding = binding


        //뒤로가기버튼
        mBinding?.signUpingBackImg?.setOnClickListener{
            it.findNavController().navigate(R.id.action_planViewFragment_to_diaryFragment)
        }

        mBinding?.fragmentPlanDiaryWriteBt?.setOnClickListener {
            it.findNavController().navigate(R.id.action_planViewFragment_to_diaryWritingFragment)
        }

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * 계획 상세보기 api 연결
         * 작성자 : 윤성식
         */
        val args : PlanViewFragmentArgs by navArgs()
        val planId = args.planId
        Log.d(TAG, "onViewCreated: $planId")

        //공유할 사람 초대하는 프래그먼트로 이동
        //planId 를 전달해야함. -> 프래그먼트 이동시 다시 x버튼을 클릭 했을 때 이 화면으로 돌아와야하기 떄문에 planId를 계속 전달해야함
        mBinding?.fragmentPlanViewNewFriendLayout?.setOnClickListener{
            val action = PlanViewFragmentDirections.actionPlanViewFragmentToPlanFriendInviteFragment(planId)
            it.findNavController().navigate(action)
        }

        val call: Call<ResponsePlanViewData> = ServiceCreator.planService.getDiaryPlanView(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            planId.toInt()
        )

        call.enqueue(object: Callback<ResponsePlanViewData> {
            override fun onResponse(
                call: Call<ResponsePlanViewData>,
                response: Response<ResponsePlanViewData>
            ) {
                if(response.code() == 200){
                    Log.e("reponse", "200!!~~~")
                    mBinding?.planDetailView = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })

        /**
         * 0524 계획 삭제 api 연동
         * 작성자 : 윤성식
         */
        mBinding?.fragmentPlanViewDeleteIv?.setOnClickListener{
            val call: Call<ResponsePlanWriteData> = ServiceCreator.planService.deletePlan(
                TloverApplication.prefs.getString("jwt", "null"),
                TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                planId.toInt()
            )

            call.enqueue(object: Callback<ResponsePlanWriteData> {
                override fun onResponse(
                    call: Call<ResponsePlanWriteData>,
                    response: Response<ResponsePlanWriteData>
                ) {
                    if(response.code() == 200){
                        Log.e("reponse", "200!!~~~")
                        Toast.makeText(requireActivity(), "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else if(response.code() == 403){
                        Toast.makeText(requireActivity(), "해당 계획에 삭제 권한이 없는 유저입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePlanWriteData>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
            it.findNavController().navigate(R.id.action_planViewFragment_to_diaryFragment)
        }
    }
}