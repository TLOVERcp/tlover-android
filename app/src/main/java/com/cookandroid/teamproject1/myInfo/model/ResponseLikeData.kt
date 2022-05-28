package com.cookandroid.teamproject1.myInfo.model

import com.google.gson.annotations.SerializedName

/**
 * 내 정보 좋아요한 다이어리 목록 조회 반환 값
 * 작성자 : 윤성식
 */
data class ResponseLikeData(
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
            @SerializedName("planStartDate")
            val planStartDate: String,
            @SerializedName("planEndDate")
            val planEndDate: String,
            @SerializedName("totalDay")
            val totalDay: Int,
            @SerializedName("diaryView")
            val diaryView: String,
//            @SerializedName("myFileKey")
//            val myFileKey: String,
            @SerializedName("planRegionDetail")
            val planRegionDetail: String,
            @SerializedName("expense")
            val expense: Int
        )
    }
}
