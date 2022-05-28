package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoSettingBinding
import com.cookandroid.teamproject1.databinding.FragmentMyInfoSettingWithdrawalBinding

class MyInfoSettingWithdrawalFragment : Fragment(){
    private var mBinding : FragmentMyInfoSettingWithdrawalBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoSettingWithdrawalBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

}