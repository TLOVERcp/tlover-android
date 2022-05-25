package com.cookandroid.teamproject1.diary.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * 다이어리 서비스
 * 작성자 : 윤성식
 * + 다이어리 상세조회 api 연동
 */
interface DiaryService {
    //다이어리 상세조회
    @GET("api/v1/diaries/connections/{diaryId}")
    fun getDiaryDetail(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("diaryId") diaryId : Int
    ) : Call<ResponseDiaryViewData>
}