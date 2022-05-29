package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentMyInfoPrivacyBinding
import com.cookandroid.teamproject1.databinding.FragmentMyInfoServiceBinding
import com.cookandroid.teamproject1.databinding.FragmentMyInfoVersionBinding

//이용약관 프래그먼트
class MyInfoVersionFragment: Fragment(){
    private var mBinding : FragmentMyInfoVersionBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoVersionBinding.inflate(inflater, container, false)

        mBinding = binding

        mBinding?.myInfoVersionBackIv?.setOnClickListener{
            val action = MyInfoVersionFragmentDirections.actionMyInfoVersionFragmentToMyInfoSettingFragment()
            it.findNavController().navigate(action)
        }

        return mBinding?.root
    }

}