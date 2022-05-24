package com.cookandroid.teamproject1.home.model

import com.google.gson.annotations.SerializedName

/**
 * 날씨기반 추천 api 연동 반환값
 */
data class ResponseWeatherDiaryData(
    val code: Int,
    val data: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("diaryId")
        val id: Int,
        @SerializedName("diaryTitle")
        val diaryTitle: String,
        @SerializedName("startDate")
        val startDate: String,
        @SerializedName("regionName")
        val diaryRegion: ArrayList<String>,
        @SerializedName("image")
        val image: ArrayList<String>,
        @SerializedName("userNickName")
        val userNickname: String,
        @SerializedName("weatherTciGrade")
        val weatherTciGrade : String
    )

}
