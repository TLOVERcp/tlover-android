package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryBinding
import com.cookandroid.teamproject1.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class DiaryFragment : Fragment() {
    private var binding : FragmentDiaryBinding?= null
    private var information = arrayListOf("여행 계획", "라이브러리")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryBinding.inflate(inflater, container, false)

        val diaryAdapter = DiaryVPAdapter(this)
        binding.diaryContentVp.adapter = diaryAdapter
        TabLayoutMediator(binding.diaryContentTb, binding.diaryContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}