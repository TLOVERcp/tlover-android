package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentPlanWriteBinding

/**
 * 계획작성 프래그먼트
 * 현재 테스트중(홈화면 변경을 여기서 테스트중)
 */
class PlanWriteFragment : Fragment(){
    lateinit var binding: FragmentPlanWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanWriteBinding.inflate(inflater, container, false)
        binding.fragmentHomeMainFirstBtn.bringToFront()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}