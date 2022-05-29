package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName

data class RequestScrapWhetherData(
    @SerializedName("diaryId")
    val diaryId: Int
)
