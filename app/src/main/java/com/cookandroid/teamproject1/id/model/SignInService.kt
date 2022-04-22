package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 작성자 : 윤성식, 이충환
 * 유저 로그인 api 연동
 */
interface SignInService {
    @POST("/api/v1/users/login")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}