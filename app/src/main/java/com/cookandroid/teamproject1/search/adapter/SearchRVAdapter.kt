package com.cookandroid.teamproject1.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemSearchViewBinding
import com.cookandroid.teamproject1.search.model.ResponseSearchDiary
import com.cookandroid.teamproject1.search.view.SearchFragmentDirections

/**
 * 검색화면 리사이클러뷰 어댑터
 */
class SearchRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var searchDiary = mutableListOf<ResponseSearchDiary.Result.Result2>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemSearchViewBinding = ItemSearchViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(searchDiary[position])
    }

    override fun getItemCount(): Int = searchDiary.size

    inner class ViewHolder(
        private val binding: ItemSearchViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(diarySearchList: ResponseSearchDiary.Result.Result2){
            binding.searchDiary = diarySearchList

            /**
             * 검색화면에서 나오는 다이어리 하나를 클릭했을 때 다이어리 상세뷰로 가는 코드
             * 해당 다이어리만 나와야 하기 때문에 다이어리 id를 들고 다이어리 상세 프래그먼트로감
             */
            itemView.setOnClickListener{
                //action_searchFragment_to_diaryViewFragment
                val action = SearchFragmentDirections.actionSearchFragmentToDiaryViewFragment(diarySearchList.id)
                it.findNavController().navigate(action)
            }
        }
    }
}

