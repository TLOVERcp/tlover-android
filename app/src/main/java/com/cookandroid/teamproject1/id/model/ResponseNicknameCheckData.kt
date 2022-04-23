package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

/**
 * 닉네임중복체크에 대한 결과
 */
//data class ResponseIdCheckData(
//    val code: Int,
//    val result: IdCheckData?
//){
//    data class IdCheckData(
//        @SerializedName("message")
//        val message : String
//    )
//}

data class ResponseNicknameCheckData(
    val code: Int,

    @SerializedName("message")
    val message : String
)