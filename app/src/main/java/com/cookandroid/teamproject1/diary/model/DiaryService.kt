package com.cookandroid.teamproject1.diary.model

import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import retrofit2.Call
import retrofit2.http.*
import java.io.File

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

}