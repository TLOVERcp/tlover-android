package com.cookandroid.teamproject1.home.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * 홈화면 다이어리 목록 조회 api
 */
interface DiaryService {
    @GET("/api/v1/diaries/get-diary")
    fun getDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ): Call<ResponseAllDiaryData>
}