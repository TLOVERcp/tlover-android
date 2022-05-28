package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseDiaryPlanId (
    val code: Int,
    val message : String,
    val data : Result
    )
    {
        data class Result(
            @SerializedName("planId")
            val planId: Long
        )
    }
