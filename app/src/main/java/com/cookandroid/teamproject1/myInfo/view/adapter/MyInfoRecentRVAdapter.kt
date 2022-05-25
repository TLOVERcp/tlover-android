package com.cookandroid.teamproject1.myInfo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemRecentViewBinding
import com.cookandroid.teamproject1.databinding.ItemSearchViewBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseRecentData

/**
 * 최근 본 다이어리 리사이클러뷰 어댑터
 */
class MyInfoRecentRVAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var recentDiary = mutableListOf<ResponseRecentData.Result>()

    inner class ViewHolder(
        private val binding : ItemRecentViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(diaryRecentList : ResponseRecentData.Result){
            binding.recentDiary = diaryRecentList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemRecentViewBinding = ItemRecentViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(recentDiary[position])
    }

    override fun getItemCount(): Int {
        return recentDiary.size
    }
}