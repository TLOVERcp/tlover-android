package com.cookandroid.teamproject1.search.model

import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import com.google.gson.annotations.SerializedName

/**
 * 검색 화면에서 다이어리 검색하였을 때 반환하는 데이터
 * 작성자 : 윤성식
 */
data class ResponseSearchDiary(
    val code: Int,
    val message : String,
    val data : Result
)
{
    data class Result(
        @SerializedName("totalPage")
        val totalPage: Int,
        @SerializedName("currentPage")
        val currentPage: Int,
        @SerializedName("totalElements")
        val totalElements: Int,
        @SerializedName("currentElements")
        val currentElements: Int,
        @SerializedName("hasPrevious")
        val hasPrevious: Boolean,
        @SerializedName("hasNext")
        val hasNext: Boolean,

        val data : ArrayList<Result2>
    )
    {
        data class Result2(
            @SerializedName("diaryId")
            val id: Int,
            @SerializedName("diaryTitle")
            val diaryTitle: String,
            @SerializedName("diaryStatus")
            val diaryStatus: String,
            @SerializedName("diaryContext")
            val diaryContext: String,
            @SerializedName("diaryStartDate")
            val diaryStartDate: String,
            @SerializedName("diaryWriteDate")
            val diaryWriteDate: String,
            @SerializedName("diaryEndDate")
            val diaryEndDate: String,
            @SerializedName("diaryImg")
            val diaryImg: String,
            @SerializedName("totalCost")
            val totalCost: Int,
            @SerializedName("themaNames")
            val themaNames: ArrayList<String>,
            @SerializedName("regionNames")
            val regionNames: ArrayList<String>
        )
    }
}
