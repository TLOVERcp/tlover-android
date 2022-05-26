package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseLikeData(
    val code: Int,
    val message: String,
    val data : Result
    )
{
    data class Result(
        @SerializedName("diaryIikedId")
        val diaryIikedId: Int,
        @SerializedName("liked")
        val liked: Boolean
    )
}
