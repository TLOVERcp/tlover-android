package com.cookandroid.teamproject1.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * data binding 사용
 * + 홈 api 홈 화면 여행 취향 다이어리 목록 사진 연동 : 윤성식 0508
 */
object BindingConversion {

    @JvmStatic
    @BindingAdapter("loadImg")
    fun loadImg(imageView: ImageView, url: String){
        Glide.with(imageView.context).load("https://d1mwr8154tagzz.cloudfront.net/$url")
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setImg")
    fun setImg(imageView: ImageView, day: String){
        if (day == "-1"){
//            imageView.isGone = true
            imageView.isInvisible = true
            Log.e("img", "success")
        }
    }

    @JvmStatic
    @BindingAdapter("setTitle")
    fun setTitle(textView: TextView, title: String?){
        if (title.equals("-1")){
            textView.isGone = true
//            textView.isInvisible = true

        }else{
            textView.text = "여행 "+title+"일차"
        }
    }

    @JvmStatic
    @BindingAdapter("setRegion")
    fun setRegion(textView: TextView, region: String){
        textView.text = region.substring(1, region.length-1)
    }
}
