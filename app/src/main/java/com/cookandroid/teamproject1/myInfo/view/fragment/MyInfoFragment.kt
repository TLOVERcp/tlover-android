package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoBinding
import com.cookandroid.teamproject1.myInfo.view.adapter.MyInfoVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 내정보화면
 */
class MyInfoFragment : Fragment(){

    private var mBinding : FragmentMyInfoBinding?= null

    private var information = arrayListOf("스크랩", "좋아요", "최근 본")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoBinding.inflate(inflater, container, false)

        mBinding = binding

        val myInfoVPAdapter = MyInfoVPAdapter(this)
        mBinding?.fragmentMyInfoVp?.adapter = myInfoVPAdapter
        TabLayoutMediator(binding.fragmentMyInfoTb, binding.fragmentMyInfoVp){
                tab, position ->
            tab.text = information[position]
        }.attach()

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}