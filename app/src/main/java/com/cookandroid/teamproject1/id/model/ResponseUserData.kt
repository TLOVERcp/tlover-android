package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

/**
 * 유저 회원가입 response
 * 작성자 : 윤성식
 * 보완필요 *********
 */
data class ResponseUserData(
    val code: Int,

    @SerializedName("message")
    val message : String
)