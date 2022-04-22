package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

data class RequestSMSData(
    @SerializedName("phoneNum")
    val phoneNum : String
)
