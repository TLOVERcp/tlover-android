package com.cookandroid.teamproject1.diary.model

import com.cookandroid.teamproject1.plan.model.ResponseDiaryData
import com.google.gson.annotations.SerializedName

data class ResponseDiaryWriteData (
    val code: Int,
    val data: ArrayList<ResponseDiaryWriteData.Data>
) {
    data class Data(
        @SerializedName("created")
        val created: Boolean,
        @SerializedName("diaryId")
        val diaryId: Long
    )
}