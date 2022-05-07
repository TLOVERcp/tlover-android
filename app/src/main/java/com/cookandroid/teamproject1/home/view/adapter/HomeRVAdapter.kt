package com.cookandroid.teamproject1.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomBinding
import com.cookandroid.teamproject1.home.model.HomeDataModel
import com.cookandroid.teamproject1.home.model.ResponseAllDiaryData

/**
 * 홈 화면에서 나오는 첫번째 리사이클러뷰
 * 홈 화면 여행 취향 다이어리 목록 조회
 */
class HomeRVAdapter() :RecyclerView.Adapter<HomeRVAdapter.Holder>(){

    var preferDiaryList = mutableListOf<ResponseAllDiaryData.Result>()

    inner class Holder(val binding : ItemHomeRandomBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(preferDiary: ResponseAllDiaryData.Result){
            binding.preferDiaryItem = preferDiary
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeRandomBinding = ItemHomeRandomBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(preferDiaryList[position])
    }

    override fun getItemCount(): Int {
        return  preferDiaryList.size

    }

}