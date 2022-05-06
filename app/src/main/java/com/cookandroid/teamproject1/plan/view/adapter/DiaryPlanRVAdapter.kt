package com.cookandroid.teamproject1.plan.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData

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


        }
    }

}