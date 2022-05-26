package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

/**
 * 다이어리 상세 조회 데이터
 */
data class ResponseDiaryViewData(
    val code: Int,
    val message: String,
    val data : Result
)
{
    data class Result(
        @SerializedName("diaryId")
        val diaryId:Int,
        @SerializedName("diaryTitle")
        val diaryTitle:String,
        @SerializedName("diaryPublicStatus")
        val diaryPublicStatus:String,
        @SerializedName("diaryStatus")
        val diaryStatus:String,
        @SerializedName("diaryContext")
        val diaryContext:String,
        @SerializedName("diaryStartDate")
        val diaryStartDate:String,
        @SerializedName("diaryWriteDate")
        val diaryWriteDate:String,
        @SerializedName("diaryEndDate")
        val diaryEndDate:String,
        @SerializedName("diaryView")
        val diaryView:String,
        @SerializedName("myFileKeys")
        val myFileKeys: ArrayList<String>,
        @SerializedName("diaryConnectionCount")
        val diaryConnectionCount:Int,
        @SerializedName("regionDetail")
        val regionDetail:String,
        @SerializedName("totalCost")
        val totalCost:Int,
        @SerializedName("diaryThema")
        val diaryThema: ArrayList<String>,
    )
}
