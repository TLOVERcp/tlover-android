package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryPlanBinding

class DiaryPlanFragment : Fragment() {
    lateinit var binding : FragmentDiaryPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryPlanBinding.inflate(inflater, container, false)
        return binding.root
    }
}