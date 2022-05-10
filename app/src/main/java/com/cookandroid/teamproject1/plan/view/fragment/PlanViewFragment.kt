package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.databinding.FragmentPlanViewBinding
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel.Companion.TAG

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

        val args : PlanViewFragmentArgs by navArgs()
        val planId = args.planId
        Log.d(TAG, "onViewCreated: $planId")


        return mBinding?.root
    }
}