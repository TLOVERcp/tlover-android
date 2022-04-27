package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 유저회원가입 api 연결
 * 작성자 : 윤성식
 * 서버 에러로 인해 잠시 개발 중지
 * 현재 LiveData로 createAccount, signup Acitivty에서 update까지 진행함
 *
 * + 최종적으로 값 전달 확인, jwt 토큰 에러 발생
 */
interface SignUpService {
    @POST("/api/v1/users/signup")
    fun postSignUp(
        @Body body : RequestUserData
    ): Call<ResponseUserData>
}