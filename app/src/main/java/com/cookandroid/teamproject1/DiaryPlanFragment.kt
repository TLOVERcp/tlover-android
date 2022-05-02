package com.cookandroid.teamproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.teamproject1.databinding.FragmentDiaryPlanBinding

class DiaryPlanFragment : Fragment() {

    private var mBinding : FragmentDiaryPlanBinding?=null
    private var dataList = ArrayList<DiaryPlanDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryPlanBinding.inflate(inflater, container, false)
        mBinding = binding
        dataList.apply{
            add(DiaryPlanDataModel("title1", "1","29","2022.02.06","Pyeongtaek","10209","17855 bijeon","비전동 투어 우와우와"))
            add(DiaryPlanDataModel("title2", "1","26","2022.02.09","Seoul","30206","17811 Namgajwa","서대문구 짱짱"))
            add(DiaryPlanDataModel("title3", "1","58","2022.05.08","Jeju","60508","17822 Aewol","제주 애월 꺄악"))
            add(DiaryPlanDataModel("title1", "1","29","2022.02.06","Pyeongtaek","10209","17855 bijeon","비전동 투어 우와우와"))
            add(DiaryPlanDataModel("title2", "1","26","2022.02.09","Seoul","30206","17811 Namgajwa","서대문구 짱짱"))
            add(DiaryPlanDataModel("title3", "1","58","2022.05.08","Jeju","60508","17822 Aewol","제주 애월 꺄악"))

        }

        val diaryPlanRVAdapter=DiaryPlanRVAdapter(dataList)
        binding.fragmentDiaryPlanRandomRv.adapter = diaryPlanRVAdapter
        binding.fragmentDiaryPlanRandomRv.layoutManager = LinearLayoutManager(context)

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}