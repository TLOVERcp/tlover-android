package com.cookandroid.teamproject1.myInfo.model

import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import retrofit2.Call
import retrofit2.http.*

/**
 * 내정보 관련 api 연동
 * 작성자 : 윤성식
 */
interface MyInfoService {
    @GET("/api/v1/diaries/connections/get-history")
    fun getHistoryDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseRecentData>
}
