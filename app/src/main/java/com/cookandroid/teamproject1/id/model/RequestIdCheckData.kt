package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

/**
 * 아이디중복체크를 위해
 * 서버에 요청하는 데이터 아이디
 */
data class RequestIdCheckData(
    @SerializedName("loginId")
    val userId : String
)