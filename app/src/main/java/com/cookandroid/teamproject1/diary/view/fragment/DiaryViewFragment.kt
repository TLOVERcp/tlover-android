package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding

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
}