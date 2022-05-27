package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseScrapData(
    val code: Int,
    val message: String,
    val data : Result
) {
    data class Result(
        @SerializedName("created")
        val created: Boolean,
        @SerializedName("scrapId")
        val scrapId: Int
    )
}