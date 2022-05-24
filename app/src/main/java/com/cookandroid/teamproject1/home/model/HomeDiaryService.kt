package com.cookandroid.teamproject1.home.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * 홈화면 다이어리 목록 조회 api
 * 홈 화면 여행 취향 다이어리 목록 조회 추가
 * 작성자 : 윤성식
 * +홈 화면 핫한 여행지 목록 조회 추가 0509
 */
interface HomeDiaryService {
    @GET("/api/v1/diaries/get-diary-prefer")
    fun getDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ): Call<ResponseAllDiaryData>

    @GET("/api/v1/scraps/ranking?")
    fun getHotDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ): Call<ResponseHotDiaryData>

    @GET("/api/v1/diaries/get-diary-weather")
    fun getWeatherDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ): Call<ResponseWeatherDiaryData>
}