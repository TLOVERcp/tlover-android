package com.cookandroid.teamproject1.myInfo.model

import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import retrofit2.Call
import retrofit2.http.*

/**
 * 내정보 관련 api 연동
 * 작성자 : 윤성식
 * + 최근본 다이어리 목록 조회 : 윤성식
 * + 스크랩한 다이어리 목록 조회 : 윤성식
 * + 좋아요한 다이어리 목록 조회 : 윤성식
 */
interface MyInfoService {
    //최근 본 다이어리 목록 조회
    @GET("/api/v1/diaries/connections/get-history")
    fun getHistoryDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseRecentData>

    //스크랩한 다이어리 목록 조회
    @POST("/api/v1/diaries/myScrap")
    fun getLikeDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseScrapData>

    //좋아요한 다이어리 목록 조회
    @POST("/api/v1/diaries/myLiked")
    fun getScrapDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseLikeData>
}
