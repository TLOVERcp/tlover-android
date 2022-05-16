package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * 계획 사용자 권한 요청 반환 데이터
 */
data class ResponseAuthUserData (
    val code: Int,
    @SerializedName("message")
    val message : String
)




