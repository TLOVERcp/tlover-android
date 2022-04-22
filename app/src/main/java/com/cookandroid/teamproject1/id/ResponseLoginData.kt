package com.cookandroid.teamproject1.id

import com.google.gson.annotations.SerializedName

/**
 * 서버에서 받는 데이터
 * 로그인에 성공하면 jwt, message, refresh token 받음
 */
data class ResponseLoginData(
    val code: Int,
    val result: LoginData?
){
    data class LoginData(
        @SerializedName("accessJwt")
        val jwt : String,
        @SerializedName("message")
        val message : String,
        @SerializedName("refreshJwtIdx")
        val refreshToken: Long
    )
}