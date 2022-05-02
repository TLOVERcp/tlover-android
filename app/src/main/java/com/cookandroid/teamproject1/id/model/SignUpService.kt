package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 유저회원가입 api 연결
 * 작성자 : 윤성식
 */
interface SignUpService {
    @POST("/api/v1/users/signup")
    fun postSignUp(
        @Body body : RequestUserData
    ): Call<ResponseUserData>
}