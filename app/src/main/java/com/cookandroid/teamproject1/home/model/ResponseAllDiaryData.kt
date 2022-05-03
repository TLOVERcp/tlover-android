package com.cookandroid.teamproject1.home.model

import com.google.gson.annotations.SerializedName

/**
 * 홈 화면 api 연동
 * 다이어리 목록 조회 반환 값
 * 작성자 : 윤성식
 */
data class ResponseAllDiaryData(
    val code: Int,

    @SerializedName("message")
    val message : String
)
