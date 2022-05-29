package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentMyInfoServiceBinding

//이용약관 프래그먼트
class MyInfoServiceFragment: Fragment(){
    private var mBinding : FragmentMyInfoServiceBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoServiceBinding.inflate(inflater, container, false)

        mBinding = binding

        mBinding?.myInfoServiceBackIv?.setOnClickListener{
            var action = MyInfoServiceFragmentDirections.actionMyInfoServiceFragmentToMyInfoSettingFragment()
            it.findNavController().navigate(action)
        }

        return mBinding?.root
    }

}