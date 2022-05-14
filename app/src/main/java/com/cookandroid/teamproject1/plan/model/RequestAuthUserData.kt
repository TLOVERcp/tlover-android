package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * 사용자에게 권한 요청할 때 필요한 닉네임 데이터
 */
data class RequestAuthUserData(
    @SerializedName("userNickName")
    val userNickname : String
)