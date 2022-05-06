package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentPlanWriteBinding

/**
 * 계획작성 프래그먼트
 */
class PlanWriteFragment : Fragment(){
    lateinit var binding: FragmentPlanWriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanWriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signUpingBackImg.setOnClickListener(){
            it.findNavController().navigate(R.id.action_planWriteFragment_to_homeFragment)
        }
    }
}