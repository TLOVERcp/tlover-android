package com.cookandroid.teamproject1.myInfo.model

import com.google.gson.annotations.SerializedName

/**
 * 로그아웃 했을 때 반환 값
 * 회원탈퇴 했을 때 반환 값
 * 작성자 : 윤성식
 */
data class ResponseLogoutData(
    val code: Int,
    val message : String
)

