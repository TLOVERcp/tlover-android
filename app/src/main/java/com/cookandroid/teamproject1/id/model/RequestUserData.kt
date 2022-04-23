package com.cookandroid.teamproject1.id.model

import com.google.gson.annotations.SerializedName
/**
 * 유저 회원가입 request
 * 작성자 : 윤성식
 * 보완필요 *********
 */
data class RequestUserData(
    @SerializedName("loginId")
    var userId : String,
    @SerializedName("password")
    var userPw : String,
    @SerializedName("userNickName")
    var userNickname : String,
    @SerializedName("userPhoneNum")
    var userPhone : String,
    @SerializedName("userRegions")
    var userReg : String,
    @SerializedName("userThemaName")
    var userThemaName : String,
)
