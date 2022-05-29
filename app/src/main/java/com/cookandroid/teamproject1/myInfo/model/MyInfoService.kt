package com.cookandroid.teamproject1.myInfo.model

import com.cookandroid.teamproject1.id.model.RequestLoginData
import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import retrofit2.Call
import retrofit2.http.*

/**
 * 내정보 관련 api 연동
 * 작성자 : 윤성식
 * + 최근본 다이어리 목록 조회 : 윤성식
 * + 스크랩한 다이어리 목록 조회 : 윤성식
 * + 좋아요한 다이어리 목록 조회 : 윤성식
 * + 로그아웃 : 윤성식
 * + 회원탈퇴 : 윤성식
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
    fun getScrapDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseScrapData>

    //좋아요한 다이어리 목록 조회
    @POST("/api/v1/diaries/myLiked")
    fun getLikeDiary(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseLikeData>

    //로그아웃
    @GET("/api/v1/users/logout")
    fun getLogout(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponseLogoutData>

    //회원탈퇴
    @POST("/api/v1/users/withdraw")
    fun userWithdraw(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body: RequestWithdrawData
    ) : Call<ResponseLogoutData> //로그아웃 했을 때랑 반환 값이 같아 Response 를 같이 사용 중
}
