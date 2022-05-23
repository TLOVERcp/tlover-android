package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * plan 목록 조회 데이터
 */
data class ResponseDiaryPlanData (
    val code: Int,
    val data: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("planId")
        val id: Long,
        @SerializedName("planTitle")
        val planTitle: String,
        @SerializedName("planStatus")
        val planStatus: String,
        @SerializedName("planStartDate")
        val planStartDate: String,
        @SerializedName("planEndDate")
        val planEndDate: String,
        @SerializedName("day")
        val day: Long,
        @SerializedName("regionDetail")
        val regionDetail: String,
        @SerializedName("expense")
        val expense: Long
    )
}




