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
            add(DiaryPlanDataModel("Travel1","여행 36일차","2021.04.25~","Brooklyn","100,000"))
            add(DiaryPlanDataModel("Travel2","여행 91일차","2021.09.01~","Seoul","200,000"))
            add(DiaryPlanDataModel("Travel1","여행 36일차","2021.04.25~","Brooklyn","100,000"))
            add(DiaryPlanDataModel("Travel2","여행 91일차","2021.09.01~","Seoul","200,000"))
            add(DiaryPlanDataModel("Travel1","여행 36일차","2021.04.25~","Brooklyn","100,000"))
            add(DiaryPlanDataModel("Travel2","여행 91일차","2021.09.01~","Seoul","200,000"))

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