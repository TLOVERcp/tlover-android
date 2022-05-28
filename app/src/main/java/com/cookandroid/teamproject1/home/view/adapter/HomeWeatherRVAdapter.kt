package com.cookandroid.teamproject1.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomThirdBinding
import com.cookandroid.teamproject1.home.model.ResponseWeatherDiaryData
import com.cookandroid.teamproject1.home.view.fragment.HomeFragmentDirections

/**
 * 날씨 기반 추천 리사이클러뷰 어댑터
 */
class HomeWeatherRVAdapter() :RecyclerView.Adapter<HomeWeatherRVAdapter.Holder>() {
    var weatherDiaryList = mutableListOf<ResponseWeatherDiaryData.Result>()

    inner class Holder(val binding : ItemHomeRandomThirdBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(weatherDiary: ResponseWeatherDiaryData.Result){
            binding.weatherDiaryItem = weatherDiary
            //날씨 추천 목록 조회에서 item하나를 클릭하였을 때 다이어리 id를 전달함.
            itemView.setOnClickListener{
                val action = HomeFragmentDirections.actionHomeFragmentToDiaryViewFragment(diaryId = weatherDiary.id, start = 2)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeRandomThirdBinding = ItemHomeRandomThirdBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(weatherDiaryList[position])
    }

    override fun getItemCount(): Int {
        return weatherDiaryList.size
    }

}