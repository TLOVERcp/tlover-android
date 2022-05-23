package com.cookandroid.teamproject1.search.model

import com.cookandroid.teamproject1.plan.model.ResponsePlanUserData
import retrofit2.Call
import retrofit2.http.*

interface SearchService {
    //다이어리 검색 조회
    @GET("/api/v1/search/get-diary?")
    fun getSearchDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("keyword") keyword : String
    ) : Call<ResponseSearchDiary>
}
