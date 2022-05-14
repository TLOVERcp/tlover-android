package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentPlanAuthListBinding

/**
 *  계획 권한 요청받은 리스트 목록 프래그먼트
 */
class PlanAuthListFragment : Fragment() {
    private var mBinding : FragmentPlanAuthListBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanAuthListBinding.inflate(inflater, container, false)
        mBinding = binding


        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //뒤로가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener{
            it.findNavController().navigate(R.id.action_planAuthListFragment_to_homeFragment)
        }
    }
}