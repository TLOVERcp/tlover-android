package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoBinding
import com.cookandroid.teamproject1.myInfo.view.adapter.MyInfoVPAdapter
import com.cookandroid.teamproject1.util.TloverApplication
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


        //내 정보 화면에서 닉네임, 평점, 관심지역 테마 받아오기
        val regionText = "%s".format(TloverApplication.prefs.getString("userRegionName", "null"))
        val regionTextLast = regionText.substring(1, regionText.length-1)

        val themeText = "%s".format(TloverApplication.prefs.getString("userThemaName", "null"))
        val themeTextLast = themeText.substring(1, themeText.length-1)

        mBinding?.fragmentMyInfoStarTv?.text = "%s".format(TloverApplication.prefs.getString("userRating", "null"))
        mBinding?.fragmentMyInfoNicknameTv?.text = "%s".format(TloverApplication.prefs.getString("userNickname", "null"))
        mBinding?.fragmentMyInfoRegionTv?.text = regionTextLast
        mBinding?.fragmentMyInfoThemeTv?.text = themeTextLast


        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}