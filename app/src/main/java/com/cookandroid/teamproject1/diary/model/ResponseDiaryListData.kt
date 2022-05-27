package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseDiaryListData (
    val code: Int,
    val message: String,
    val data: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("diaryId")
        val diaryId: Long,
        @SerializedName("diaryTitle")
        val diaryTitle: String,
        @SerializedName("diaryContext")
        val diaryContext: String,
        @SerializedName("diaryStatus")
        val diaryStatus: String,
        @SerializedName("diaryStartDate")
        val diaryStartDate: String,
        @SerializedName("diaryEndDate")
        val diaryEndDate: String,
        @SerializedName("regionNames")
        val regionNames: ArrayList<String>,
        @SerializedName("themaNames")
        val themaNames: ArrayList<String>

    )
}