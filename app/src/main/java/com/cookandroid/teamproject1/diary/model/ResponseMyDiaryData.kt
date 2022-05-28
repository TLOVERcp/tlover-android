package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class ResponseMyDiaryData(
    val code: Int,
    val message: String,
    val data: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("diaryId")
        val diaryId: Int,
        @SerializedName("planId")
        val planId: Int,
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
        @SerializedName("diaryWriteDate")
        val diaryWriteDate: String,
        @SerializedName("regionNames")
        val regionNames: String,
        @SerializedName("themaNames")
        val themaNames: ArrayList<String>

    )
}

