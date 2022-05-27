package com.cookandroid.teamproject1.diary.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class RequestDiaryWriteData(
    @SerializedName("diaryContext")
    var diaryContext: String,
    @SerializedName("diaryEndDate")
    var diaryEndDate: String,
    @SerializedName("diaryImages")
    var diaryImages: ArrayList<File>,
    @SerializedName("planStartDate")
    var planStartDate: String,
    @SerializedName("diaryTitle")
    var diaryTitle: String,
    @SerializedName("planId")
    var planId: Int,
    @SerializedName("regionName")
    var regionName: ArrayList<String>,
    @SerializedName("themaName")
    var themaName: ArrayList<String>,
    @SerializedName("totalCost")
    var totalCost: Int
)