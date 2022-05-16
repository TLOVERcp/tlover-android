package com.cookandroid.teamproject1.plan.model

import retrofit2.Call
import retrofit2.http.*

/**
 * 작성자 : 원도혜
 * Plan api 연동
 * +플랜 상세 조회 api 연동 : 윤성식
 * +계획에서 사용자 권한 추가 api 연동 : 윤성식
 * +계획 권한 공유 요청 보내기 api 연동 : 윤성식
 * +계획 권한 요청 받은 목록 조회 api 연동 : 윤성식
 * +계획 권환 요청 수락 api 연동 : 윤성식
 */

interface PlanService {
    //플랜 목록조회
    @GET("/api/v1/plans/plan-list?")
    fun getDiaryPlan(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
//        @Query("Status") status: String
    ) : Call<ResponseDiaryPlanData>

    //플랜 상세조회
    @GET("/api/v1/plans/plan-detail/{planId}")
    fun getDiaryPlanView(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("planId") planId : Int
//        @Query("Status") status: String
    ) : Call<ResponsePlanViewData>

    //사용자 검색조회
    @GET("/api/v1/search/get-user?")
    fun getPlanUser(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("keyword") keyword : String
    ) : Call<ResponsePlanUserData>

    //계획 권한 공유 요청 보내기
    @POST("/api/v1/authority-plans/share-plan/{planId}")
    fun postAuthUser(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("planId") planId : Int,
        @Body body : RequestAuthUserData
    ) : Call<ResponseAuthUserData>

    //계획 권한 요청 받은 목록 조회
    @GET("/api/v1/authority-plans/authority-plan-list")
    fun getPlanAuth(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ) : Call<ResponsePlanAuthData>

    //계획 권환 요청 수락
    @POST("/api/v1/authority-plans/accept-authority-plan/{authorityPlanId}")
    fun acceptPlanAuth(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Path("authorityPlanId") authorityPlanId : Int
    ) : Call<ResponseAcceptAuthData>

    //계획 권한 요청 거절 api 연동 예정
}
