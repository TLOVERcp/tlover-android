package com.cookandroid.teamproject1.plan.model

import com.cookandroid.teamproject1.id.model.ResponseDiaryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * 작성자 : 원도혜
 * diary api 연동
 */

interface PlanService {
    @GET("/api/v1/diaries/get-diary")
    fun getDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
    ) : Call<ResponseDiaryData>
}
