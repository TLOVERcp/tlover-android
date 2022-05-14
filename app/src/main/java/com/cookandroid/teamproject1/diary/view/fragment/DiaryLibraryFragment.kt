package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryLibraryBinding

/**
 * diary library view
 * 여행 계획, 다이어리 모두 작성한 것들 모음 화면
 */
class DiaryLibraryFragment : Fragment(){
    lateinit var binding: FragmentDiaryLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }
}