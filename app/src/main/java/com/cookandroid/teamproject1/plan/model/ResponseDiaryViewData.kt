package com.cookandroid.teamproject1.plan.model

import com.cookandroid.teamproject1.search.view.SearchFragmentDirections
import com.google.gson.annotations.SerializedName

data class ResponseDiaryViewData(
    val code:Int,
    val message:String,
    val data: Result
)
{
    data class Result(
        @SerializedName("diaryConnectionCount")
        val diaryConnectionCount:Int,
        @SerializedName("diaryContext")
        val diaryContext:String,
        @SerializedName("diaryEndDate")
        val diaryEndDate:String,
        @SerializedName("diaryId")
        val diaryId:Int,
        @SerializedName("diaryPublicStatus")
        val diaryPublicStatus:String,
        @SerializedName("diaryStartDate")
        val diaryStartDate:String,
        @SerializedName("diaryStatus")
        val diaryStatus:String,
        @SerializedName("diaryTitle")
        val diaryTitle:String,
        @SerializedName("diaryView")
        val diaryView:String,
        @SerializedName("diaryWriteDate")
        val diaryWriteDate:String,
        @SerializedName("myFileKeys")
        val myFileKeys:ArrayList<String>,
        @SerializedName("regionDetail")
        val regionDetail:String
    )
}
