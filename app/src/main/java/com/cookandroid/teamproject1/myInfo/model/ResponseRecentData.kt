package com.cookandroid.teamproject1.myInfo.model

import com.google.gson.annotations.SerializedName

data class ResponseRecentData(
    val code: Int,
    val message : String,
    val data : ArrayList<Result>
)
{
    data class Result(
        @SerializedName("diaryId")
        val id: Int,
        @SerializedName("diaryTitle")
        val diaryTitle: String,
        @SerializedName("diaryStartDate")
        val diaryStartDate: String,
        @SerializedName("totalCost")
        val totalCost: Int,
        @SerializedName("diaryEndDate")
        val diaryEndDate: String,
        @SerializedName("historyDate")
        val historyDate: String,
        @SerializedName("liked")
        val liked: Boolean,
        @SerializedName("regionDetail")
        val regionDetail: String,
        @SerializedName("scraped")
        val scraped: Boolean,
        @SerializedName("myFileKeys")
        val myFileKeys: ArrayList<String>
    )
}
