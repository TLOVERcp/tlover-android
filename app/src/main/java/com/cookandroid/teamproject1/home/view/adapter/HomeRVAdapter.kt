package com.cookandroid.teamproject1.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomBinding
import com.cookandroid.teamproject1.home.model.HomeDataModel

/**
 * 홈 화면에서 나오는 첫번째 리사이클러뷰
 * 다이어리 목록 조회
 */
class HomeRVAdapter(private val randomList: ArrayList<HomeDataModel>) :RecyclerView.Adapter<HomeRVAdapter.Holder>(){

    class Holder(val binding : ItemHomeRandomBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(random: HomeDataModel){
            binding.itemHomeRandomImgTitle.setText(random.title)
            binding.itemHomeRandomImg.setImageResource(random.image!!)
            binding.itemHomeRandomImgDate.setText(random.date)
            binding.itemHomeRandomImgNickname.setText(random.nickname)
            binding.itemHomeRandomImgLocation.setText(random.location)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeRandomBinding = ItemHomeRandomBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(randomList[position])
    }

    override fun getItemCount(): Int {
        return  randomList.size

    }

}