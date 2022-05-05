package com.cookandroid.teamproject1.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomBinding
import com.cookandroid.teamproject1.databinding.ItemHomeRandomSecondBinding
import com.cookandroid.teamproject1.home.model.HomeDataModel
import com.cookandroid.teamproject1.home.model.HomeHotRecommendDataModel

/**
 * 홈 화면에서 나오는 두 번째 리사이클러뷰
 * 다이어리 목록 조회
 */
class HomeHotRecommendRVAdapter(private val recommendList: ArrayList<HomeHotRecommendDataModel>) :RecyclerView.Adapter<HomeHotRecommendRVAdapter.Holder>(){

    class Holder(val binding : ItemHomeRandomSecondBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(random: HomeHotRecommendDataModel){
            binding.itemHomeRandomSecondImg.setImageResource(random.image!!)
            binding.itemHomeRandomSecondDate.setText(random.date)
            binding.itemHomeRandomSecondImgTitle.setText(random.title)
            binding.itemHomeRandomSecondLikeTextview.setText(random.like)
            binding.itemHomeRandomSecondLocation.setText(random.location)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeRandomSecondBinding = ItemHomeRandomSecondBinding.inflate(
            LayoutInflater.from(viewGroup.context),viewGroup,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(recommendList[position])
    }

    override fun getItemCount(): Int {
        return recommendList.size
    }
}