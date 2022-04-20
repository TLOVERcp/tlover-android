package com.cookandroid.teamproject1.id

import com.google.gson.annotations.SerializedName

data class RequestLoginData(
    @SerializedName("loginId")
    val userId : String,
    @SerializedName("password")
    val userPw : String,
)