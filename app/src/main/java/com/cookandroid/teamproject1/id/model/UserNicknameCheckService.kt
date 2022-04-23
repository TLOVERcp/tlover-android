package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * 작성자 : 윤성식
 * 유저 아이디 중복체크 서버연결
 */
interface UserNicknameCheckService {
    @POST("/api/v1/users/nickname-duplicate-check")
    fun NicknameCheck(
        @Body body: RequestNicknameCheckData
    ): Call<ResponseNicknameCheckData>
}