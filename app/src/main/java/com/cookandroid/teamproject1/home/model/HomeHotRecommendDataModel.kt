package com.cookandroid.teamproject1.home.model

/**
 *  home 두번째 리사이클러뷰 데이터모델
 */
data class HomeHotRecommendDataModel(
    var title: String?="",
    var image: Int? = null,
    var date: String? ="",
    var like: String? ="",
    var location: String? =""
)
