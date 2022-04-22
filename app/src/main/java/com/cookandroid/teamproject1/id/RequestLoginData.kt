package com.cookandroid.teamproject1.id

import com.google.gson.annotations.SerializedName

/**
 * 서버에 요청하는 데이터
 * 로그인하기위한 id와 password요청
 */
data class RequestLoginData(
    @SerializedName("loginId")
    val userId : String,
    @SerializedName("password")
    val userPw : String,
)