package com.cookandroid.teamproject1.diary.model

import com.cookandroid.teamproject1.plan.model.RequestAuthUserData
import retrofit2.Call
import retrofit2.http.*
import java.io.File

/**
 * 다이어리 서비스
 * 작성자 : 윤성식
 * + 다이어리 상세조회 api 연동
 */
/**
 * 작성자 : 원도혜
 */
interface DiaryService {
        // 다이어리 목록 조회
        @GET("/api/v1/diaries/my-diaries")
        fun getDiaryList(
            @Header("X-ACCESS-TOKEN") jwt: String,
            @Header("X-REFRESH-TOKEN") refreshToken: Int
        ) : Call<ResponseDiaryListData>

        // 다이어리 작성
        @POST("/api/v1/diaries/create-diary")
        fun postDiaryWrite(
            @Header("X-ACCESS-TOKEN") jwt: String,
            @Header("X-REFRESH-TOKEN") refreshToken: Int,
            @Query("diaryTitle") diaryTitle : String,
            @Query("diaryContext") diaryContext : String,
            @Query("planId") planId : Int,
            @Query("diaryImages") diaryImages : ArrayList<File>
        ) : Call<ResponseDiaryWriteData>

        //다이어리 상세조회
    @GET("api/v1/diaries/connections/{diaryId}")
    fun getDiaryDetail(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("diaryId") diaryId : Int
    ) : Call<ResponseDiaryViewData>

    //스크랩 수 조회
    @GET("api/v1/scraps/{diaryId}")
    fun getScrapNum(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("diaryId") diaryId : Int
    ) : Call<ResponseDiaryScrapNumData>

    //좋아요 수 조회
    @POST("api/v1/diaries/liked-views/{diaryId}")
    fun getLikeNum(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("diaryId") diaryId : Int
    ) : Call<ResponseDiaryLikeNumData>

    //좋아요 누르기
    @POST("api/v1/diaries/liked/{diaryId}")
    fun getLike(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("diaryId") diaryId : Int
    ) : Call<ResponseLikeData>

    //스크랩 누르기
    @POST("api/v1/scraps")
    fun getScrap(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body : RequestScrapData
    ) : Call<ResponseScrapData>



}