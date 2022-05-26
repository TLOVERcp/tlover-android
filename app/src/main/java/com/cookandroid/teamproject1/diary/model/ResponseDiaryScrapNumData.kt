package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseDiaryScrapNumData(
    val code: Int,
    val message : String,
    val data : Result
)
{
    data class Result(
        @SerializedName("scrapCount")
        val scrapCount: Int
    )
}
