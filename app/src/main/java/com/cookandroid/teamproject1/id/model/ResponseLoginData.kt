package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

/**
 * 서버에서 받는 데이터
 * 로그인에 성공하면 jwt, message, refresh token 받음
 * 5.26 내정보 화면에서의 필요한 정보가 있어서 변경 + 윤성식
 */
//data class ResponseLoginData(
//    val code: Int,
//    val result: LoginData?
//){
//    data class LoginData(
//        @SerializedName("accessJwt")
//        val jwt : String,
//        @SerializedName("message")
//        val message : String,
//        @SerializedName("refreshJwtIdx")
//        val refreshToken: Long
//    )
//}

data class ResponseLoginData(
    val code: Int,

    @SerializedName("accessJwt")
    val jwt : String,
    @SerializedName("message")
    val message : String,
    @SerializedName("refreshJwtIdx")
    val refreshToken: Long,
    @SerializedName("userNickname")
    val userNickname: String,
    @SerializedName("userThemaName")
    val userThemaName: ArrayList<String>,
    @SerializedName("userRegionName")
    val userRegionName: ArrayList<String>,
    @SerializedName("userRating")
    val userRating: Float
)
