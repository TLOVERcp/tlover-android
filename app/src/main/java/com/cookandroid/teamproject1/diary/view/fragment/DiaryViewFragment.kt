package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.view.fragment.PlanViewFragmentArgs

/**
 * diary 상세보기 프래그먼트
 */
class DiaryViewFragment : Fragment(){
    private var mBinding : FragmentDiaryViewBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryViewBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args : DiaryViewFragmentArgs by navArgs()
        val diaryId = args.diaryId
        Log.d(SignUpViewModel.TAG, "onViewCreated: $diaryId")

        super.onViewCreated(view, savedInstanceState)
    }
}