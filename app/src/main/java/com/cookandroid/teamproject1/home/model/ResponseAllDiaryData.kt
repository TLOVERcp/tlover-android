package com.cookandroid.teamproject1.home.model

import com.google.gson.annotations.SerializedName

/**
 * 홈 화면 api 연동
 * 다이어리 취향 추천 목록 조회 반환 값
 * 작성자 : 윤성식
 */
data class ResponseAllDiaryData(
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
        val userNickname: String
    )

}
