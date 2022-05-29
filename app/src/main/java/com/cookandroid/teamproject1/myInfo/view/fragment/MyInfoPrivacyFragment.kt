package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentMyInfoPrivacyBinding
import com.cookandroid.teamproject1.databinding.FragmentMyInfoServiceBinding

//개인정보 처리방침 프래그먼트
class MyInfoPrivacyFragment: Fragment(){
    private var mBinding : FragmentMyInfoPrivacyBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoPrivacyBinding.inflate(inflater, container, false)

        mBinding = binding

        mBinding?.myInfoPrivacyBackIv?.setOnClickListener{
            val action = MyInfoPrivacyFragmentDirections.actionMyInfoPrivacyFragmentToMyInfoSettingFragment()
            it.findNavController().navigate(action)
        }


        return mBinding?.root
    }

}