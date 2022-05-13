package com.cookandroid.teamproject1.plan.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentPlanFriendInviteBinding

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
    }
}