package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

/**
 * 계획 상세 조회 데이터
 */
data class ResponsePlanViewData (
    val code:Int,
    val message: String,
    val data: Result
)
{
    data class Result(
        @SerializedName("day")
        val day: Int,
        @SerializedName("expense")
        val expense: Int,
        @SerializedName("planContext")
        val planContext: String,
        @SerializedName("planEndDate")
        val planEndDate: String,
        @SerializedName("planId")
        val planId: Int,
        @SerializedName("planStartDate")
        val planStartDate: String,
        @SerializedName("planStatus")
        val planStatus: String,
        @SerializedName("planTitle")
        val planTitle: String,
        @SerializedName("planWriteDate")
        val planWriteDate: String,
        @SerializedName("regionDetail")
        val regionDetail: String,
        @SerializedName("users")
        val users: ArrayList<String>
    )

}



