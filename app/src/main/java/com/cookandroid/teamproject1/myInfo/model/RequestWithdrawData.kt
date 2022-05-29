package com.cookandroid.teamproject1.myInfo.model

import com.google.gson.annotations.SerializedName

/**
 * 회원탈퇴를 위한 패스워드 전송
 */
data class RequestWithdrawData(
    @SerializedName("password")
    val password : String,
)