package com.cookandroid.teamproject1.myInfo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemScrapViewBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseScrapData
import com.cookandroid.teamproject1.myInfo.view.fragment.MyInfoFragmentDirections

/**
 * 스크랩한 다이어리 리사이클러뷰 어댑터
 */
class MyInfoScrapRVAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var scrapDiary = mutableListOf<ResponseScrapData.Result.Result2>()

    inner class ViewHolder(
        private val binding : ItemScrapViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(diaryScrapList : ResponseScrapData.Result.Result2){
            binding.scrapDiary = diaryScrapList

            itemView.setOnClickListener{
                val action = MyInfoFragmentDirections.actionMyInfoFragmentToDiaryViewFragment(diaryScrapList.id)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemScrapViewBinding = ItemScrapViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(scrapDiary[position])
    }

    override fun getItemCount(): Int {
        return scrapDiary.size
    }
}