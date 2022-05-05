package com.cookandroid.teamproject1

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding

/**
 * diary plan RecyclerView Adapter
 */
class DiaryPlanRVAdapter(private val diaryplanList: ArrayList<DiaryPlanDataModel>) :RecyclerView.Adapter<DiaryPlanRVAdapter.Holder>(){

    class Holder(val binding : ItemHomeDiaryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(random: DiaryPlanDataModel){
            binding.itemHomeDiaryTitle.setText(random.title)
            binding.itemHomeDiaryYeartext.setText(random.yearnum)
            binding.itemHomeDiaryDaytextNum.setText(random.daynum)
            binding.itemHomeDiaryCalanderText.setText(random.s_date)
            binding.itemHomeDiaryLocationText.setText(random.location)
            binding.itemHomeDiaryCoinText.setText(random.price)
            binding.itemHomeDiaryBedText.setText(random.accommodation)
            binding.itemHomeDiaryContentText.setText(random.content)
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