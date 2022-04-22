package com.cookandroid.teamproject1.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
/**
 * 작성자 : 윤성식
 * SMS 문자인증 api 연결
 */
interface SMSService {
    @POST("/api/v1/sms/send")
    fun postSendSMS(
        @Body body : RequestSMSData
    ): Call<ResponseSMSData>
}