package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

data class ResponseDiaryData (
    val code:Int,
    val result: ArrayList<Result>
)
{
    data class Result(
        @SerializedName("diaryContext")
        val context: String,
        @SerializedName("diaryEndDate")
        val endDate: String,
        @SerializedName("diaryId")
        val id: Long,
        @SerializedName("diaryPublicStatus")
        val publicStatus: String,
        @SerializedName("diaryStartDate")
        val startDate: String,
        @SerializedName("diaryStatus")
        val status: String,
        @SerializedName("diaryTitle")
        val title: String,
        @SerializedName("diaryView")
        val view: String,
        @SerializedName("diaryWriteDate")
        val writeDate: String
    )

}



