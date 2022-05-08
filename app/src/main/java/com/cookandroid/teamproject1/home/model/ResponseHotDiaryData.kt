package com.cookandroid.teamproject1.home.model

import com.google.gson.annotations.SerializedName

/**
 * 홈 화면 api 연동
 * 다이어리 핫한 목록 조회 반환 값
 * 작성자 : 윤성식
 */
data class ResponseHotDiaryData(
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
            @SerializedName("diaryPublicStatus")
            val diaryPublicStatus: String,
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
            @SerializedName("diaryView")
            val diaryView: String,
            @SerializedName("myFileKey")
            val myFileKey: String,
            @SerializedName("scrapCount")
            val scrapCount: Int
        )
    }
}
