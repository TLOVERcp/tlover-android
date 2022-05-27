package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseDiaryLikeNumData(
    val code : Int,
    val message : String,
    val data : Result

) {
    data class Result(
        @SerializedName("diaryId")
        val diaryId: Int,
        @SerializedName("diaryLikedViews")
        val diaryLikedViews : Int
    )
}
