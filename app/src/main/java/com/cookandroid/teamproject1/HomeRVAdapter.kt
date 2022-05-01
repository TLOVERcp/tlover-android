package com.cookandroid.teamproject1

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeRandomBinding

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
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeRVAdapter.Holder {
        val binding : ItemHomeRandomBinding = ItemHomeRandomBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: HomeRVAdapter.Holder, position: Int) {
        holder.bind(randomList[position])
    }

    override fun getItemCount(): Int {
        return  randomList.size

    }

}