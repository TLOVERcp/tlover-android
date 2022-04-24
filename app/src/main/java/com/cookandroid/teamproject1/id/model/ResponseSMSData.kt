package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

//data class ResponseSMSData(
//    val code: Int,
//    val result: SMSData?
//){
//    data class SMSData(
//        @SerializedName("message")
//        val message : String,
//        @SerializedName("value")
//        val certifyvalue : String
//    )
//}


data class ResponseSMSData(
    val code: Int,

    @SerializedName("message")
    val message : String,
    @SerializedName("value")
    val certifyvalue : String
)