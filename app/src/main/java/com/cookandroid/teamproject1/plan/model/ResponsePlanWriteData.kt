package com.cookandroid.teamproject1.plan.model

import com.google.gson.annotations.SerializedName

data class ResponsePlanWriteData (
    val code: Int,

    @SerializedName("message")
    val message : String
    )