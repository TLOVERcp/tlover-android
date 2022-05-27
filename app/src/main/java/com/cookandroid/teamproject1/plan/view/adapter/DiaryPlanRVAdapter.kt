package com.cookandroid.teamproject1.plan.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding
import com.cookandroid.teamproject1.diary.view.fragment.DiaryFragmentDirections
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.viewmodel.PlanDetailViewModel


class DiaryPlanRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var diaryPlanList = mutableListOf<ResponseDiaryPlanData.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemHomeDiaryBinding = ItemHomeDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(diaryPlanList[position])
    }

    override fun getItemCount(): Int = diaryPlanList.size

    inner class ViewHolder(
        private val binding: ItemHomeDiaryBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(diaryPlanProject: ResponseDiaryPlanData.Result){
            binding.diaryItem = diaryPlanProject
            Log.e(" ", "ing")
            //plan 목록 조회에서 하나 클릭하였을 때 plan id를 전달하며 상세보기페이지로 이동
            itemView.setOnClickListener{
                val action = DiaryFragmentDirections.actionDiaryFragmentToPlanViewFragment(diaryPlanProject.id)
                it.findNavController().navigate(action)
            }
        }
    }

}

