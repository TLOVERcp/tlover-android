package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * plan 권한 요청 받은 리스트 데이터
 */
data class ResponsePlanAuthData (
    val code: Int,
    val message : String,
    val data: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("authorityPlanId")
        val authorityPlanId: Int,
//        @SerializedName("authorityPlanShareDate")
//        val authorityPlanShareDate: String,
        @SerializedName("authorityPlanStatus")
        val authorityPlanStatus: String,
        @SerializedName("planStatus")
        val planStatus: String,
        @SerializedName("planTitle")
        val planTitle: String,
        @SerializedName("planUserNickName")
        val planUserNickName: String
    )
}




