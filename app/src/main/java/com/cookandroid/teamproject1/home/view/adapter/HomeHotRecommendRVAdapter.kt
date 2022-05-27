package com.cookandroid.teamproject1.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomBinding
import com.cookandroid.teamproject1.databinding.ItemHomeRandomSecondBinding
import com.cookandroid.teamproject1.home.model.HomeDataModel
import com.cookandroid.teamproject1.home.model.HomeHotRecommendDataModel
import com.cookandroid.teamproject1.home.model.ResponseAllDiaryData
import com.cookandroid.teamproject1.home.model.ResponseHotDiaryData
import com.cookandroid.teamproject1.home.view.fragment.HomeFragmentDirections

/**
 * 홈 화면에서 나오는 두 번째 리사이클러뷰
 * 다이어리 핫한 목록 조회
 */
class HomeHotRecommendRVAdapter() :RecyclerView.Adapter<HomeHotRecommendRVAdapter.Holder>(){

    var hotDiaryList = mutableListOf<ResponseHotDiaryData.Result.Result2>()

    class Holder(val binding : ItemHomeRandomSecondBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hotDiary: ResponseHotDiaryData.Result.Result2){
            binding.hotDiaryItem = hotDiary
            //핫한 다이어리 목록 조회에서 item하나를 클릭하였을 때 다이어리 id를 전달함.
            itemView.setOnClickListener{
                val action = HomeFragmentDirections.actionHomeFragmentToDiaryViewFragment(diaryId = hotDiary.id, start = 2)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeRandomSecondBinding = ItemHomeRandomSecondBinding.inflate(
            LayoutInflater.from(viewGroup.context),viewGroup,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(hotDiaryList[position])
    }

    override fun getItemCount(): Int {
        return hotDiaryList.size
    }
}