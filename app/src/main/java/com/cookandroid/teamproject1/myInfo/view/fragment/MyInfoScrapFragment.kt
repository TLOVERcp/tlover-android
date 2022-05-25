package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoScrapBinding

class MyInfoScrapFragment : Fragment() {
    private var mBinding : FragmentMyInfoScrapBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoScrapBinding.inflate(inflater, container, false)
        mBinding = binding

        return mBinding?.root
    }

}