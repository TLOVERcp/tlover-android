package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * 계획 사용자 검색 조회 데이터
 */
data class ResponsePlanUserData (
    val code: Int,
    val message : String,
    val data : Result
)
{
    data class Result(
        @SerializedName("userId")
        val id: Int,
        @SerializedName("userNickName")
        val userNickName: String
    )
}



