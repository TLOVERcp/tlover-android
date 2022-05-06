package com.cookandroid.teamproject1.plan.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding
import com.cookandroid.teamproject1.plan.model.DiaryPlanDataModel

class DiaryPlanRVAdapter(private var diaryplanList: ArrayList<DiaryPlanDataModel>) :RecyclerView.Adapter<DiaryPlanRVAdapter.Holder>(){


//    internal fun setDataList(selectDataList : ArrayList<DiaryPlanDataModel>){
//        this.diaryplanList = selectDataList
//        notifyDataSetChanged()
//    }

    class Holder(val binding : ItemHomeDiaryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(random: DiaryPlanDataModel){
            binding.itemHomeDiaryTitle.setText(random.title)
            binding.itemHomeDiaryDayOfTravel.setText(random.daynum)
            binding.itemHomeDiaryCalanderText.setText(random.s_date)
            binding.itemHomeDiaryLocationText.setText(random.location)
            binding.itemHomeDiaryCoinText.setText(random.price)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val binding : ItemHomeDiaryBinding = ItemHomeDiaryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(diaryplanList[position])
    }

    override fun getItemCount(): Int {
        return  diaryplanList.size

    }

}