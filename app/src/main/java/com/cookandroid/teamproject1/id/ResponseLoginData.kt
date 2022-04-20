package com.cookandroid.teamproject1.id

import com.google.gson.annotations.SerializedName

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