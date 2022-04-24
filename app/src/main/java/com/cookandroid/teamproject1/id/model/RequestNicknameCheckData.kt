package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName

/**
 * 닉네임중복체크를 위해
 * 서버에 요청하는 데이터 아이디
 */
data class RequestNicknameCheckData(
    @SerializedName("userNickname")
    val userNickname : String
)