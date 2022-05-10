package com.cookandroid.teamproject1.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * data binding 사용
 * + 홈 api 홈 화면 여행 취향 다이어리 목록 사진 연동 : 윤성식 0508
 */
object BindingConversions {

    @JvmStatic
    @BindingAdapter("loadImg")
    fun loadImg(imageView: ImageView, url: String){
        Glide.with(imageView.context).load("https://d1mwr8154tagzz.cloudfront.net/$url")
            .into(imageView)
    }

}
