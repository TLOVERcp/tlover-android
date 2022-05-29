package com.cookandroid.teamproject1.myInfo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemLikeViewBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseLikeData
import com.cookandroid.teamproject1.myInfo.view.fragment.MyInfoFragmentDirections

/**
 * 좋아요한 다이어리 리사이클러뷰 어댑터
 */
class MyInfoLikeRVAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var likeDiary = mutableListOf<ResponseLikeData.Result.Result2>()

    inner class ViewHolder(
        private val binding : ItemLikeViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(diaryLikeList : ResponseLikeData.Result.Result2){
            binding.likeDiary = diaryLikeList

            itemView.setOnClickListener{
                val action = MyInfoFragmentDirections.actionMyInfoFragmentToDiaryViewFragment(diaryId = diaryLikeList.id, start = 32)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemLikeViewBinding = ItemLikeViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(likeDiary[position])
    }

    override fun getItemCount(): Int {
        return likeDiary.size
    }


}