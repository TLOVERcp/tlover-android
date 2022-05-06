package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * 작성자 : 원도혜
 * diary api 연동
 */

interface DiaryService {
    @GET("/api/v1/diaries/get-diary")
    fun getDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
    ) : Call<ResponseDiaryData>
}
