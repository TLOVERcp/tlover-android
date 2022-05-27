package com.cookandroid.teamproject1.diary.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryLibraryBinding
import com.cookandroid.teamproject1.diary.model.ResponseMyDiaryData
import com.cookandroid.teamproject1.diary.view.fragment.DiaryFragmentDirections
import com.cookandroid.teamproject1.diary.view.fragment.DiaryLibraryFragmentDirections

/**
 * 내가 작성한 다이어리 목록 조회 어댑터
 */
class DiaryLibraryRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var diaryLibraryList = mutableListOf<ResponseMyDiaryData.Result>()

    inner class ViewHolder(
        private val binding : ItemHomeDiaryLibraryBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun onBind(myDiaryProject : ResponseMyDiaryData.Result){
            binding.myDiaryItem = myDiaryProject

            itemView.setOnClickListener{
                val action = DiaryLibraryFragmentDirections.actionDiaryLibraryFragmentToDiaryViewFragment(myDiaryProject.diaryId)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemHomeDiaryLibraryBinding = ItemHomeDiaryLibraryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(diaryLibraryList[position])
    }

    override fun getItemCount(): Int {
        return diaryLibraryList.size
    }
}