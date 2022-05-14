package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentDiaryBinding
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding
import com.cookandroid.teamproject1.diary.view.adapter.DiaryVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * diary view
 * plan or library view
 */
class DiaryFragment : Fragment() {
    private var mBinding : FragmentDiaryBinding?= null
//    private var iBinding : ItemHomeDiaryBinding? = null
    private var information = arrayListOf("여행 계획", "라이브러리")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryBinding.inflate(inflater, container, false)
        mBinding = binding

        val diaryAdapter = DiaryVPAdapter(this)
        binding.diaryContentVp.adapter = diaryAdapter
        TabLayoutMediator(binding.diaryContentTb, binding.diaryContentVp){
            tab, position ->
            tab.text = information[position]
        }.attach()

        mBinding?.fragmentDiaryChangeTv?.setOnClickListener(){
//            iBinding?.checkBox?.visibility =View.VISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //새로운 게획 작성 버튼을 클릭할 때 planWriteFragment로 이동
        mBinding?.fragmentPlanNewWriteB?.setOnClickListener(){
            it.findNavController().navigate(R.id.action_diaryFragment_to_planWriteFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}