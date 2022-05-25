package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

data class RequestPlanWriteData(
    @SerializedName("expense")
    var expense: Long,
    @SerializedName("planContext")
    var planContext: String,
    @SerializedName("planEndDate")
    var planEndDate: String,
    @SerializedName("planStartDate")
    var planStartDate: String,
    @SerializedName("regionName")
    var regionName: ArrayList<String>,
    @SerializedName("planTitle")
    var planTitle: String
)