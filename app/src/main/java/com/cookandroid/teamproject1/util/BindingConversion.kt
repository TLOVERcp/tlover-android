package com.cookandroid.teamproject1.util

import android.util.Log
import android.widget.Button
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
    @BindingAdapter("setCalendarStart")
    fun setCalendarStart(textView: TextView, sCalendar: String){
        textView.text = sCalendar.substring(0, 4)
    }

    @JvmStatic
    @BindingAdapter("setCalendarPlan")
    fun setCalendarPlan(textView: TextView, sCalendar: String){
        textView.text = sCalendar.substring(0, 10)
    }

    @JvmStatic
    @BindingAdapter("setCalendarEnd")
    fun setCalendarEnd(textView: TextView, eCalendar: String){
        textView.text = eCalendar.substring(0, 10)
    }

    @JvmStatic
    @BindingAdapter("setTitle")
    fun setTitle(textView: TextView, title: String?){
        if (title.equals("-1")){
            textView.isGone = true
        }else{
            textView.text = "여행 "+title+"일차"
        }
    }

    @JvmStatic
    @BindingAdapter("setRegion")
    fun setRegion(textView: TextView, region: String){
        textView.text = region.substring(1, region.length-1)
    }

    @JvmStatic
    @BindingAdapter("getToString")
    fun getToString(textView: TextView, num: Int){
        textView.text = num.toString()
    }

    //다이어리 검색뷰에서 이미지 리사이징 구현
    @JvmStatic
    @BindingAdapter("loadSearchImg")
    fun loadSearchImg(imageView: ImageView, url: String){
        Glide.with(imageView.context).load("https://d1mwr8154tagzz.cloudfront.net/$url?w=107&h=144&qq=50")
            .into(imageView)
    }

    //다이어리 상세조회 이미지 리사이징
    @JvmStatic
    @BindingAdapter("loadDetailImg")
    fun loadDetailImg(imageView: ImageView, url: String){
        Glide.with(imageView.context).load("https://d1mwr8154tagzz.cloudfront.net/$url?w=330&h=270&qq=50")
            .into(imageView)
    }
}
