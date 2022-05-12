package com.cookandroid.teamproject1.util

import com.cookandroid.teamproject1.home.model.HomeDiaryService
import com.cookandroid.teamproject1.plan.model.PlanService
import com.cookandroid.teamproject1.id.model.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 작성자 : 윤성식, 이충환
 * api 연결부분
 * +signup 추가완료
 */
object ServiceCreator {
    private const val BASE_URL = "http://52.78.245.11:8080"
    // localhost


    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val signInService : SignInService = retrofit.create(SignInService::class.java)
    val signUpService : SignUpService = retrofit.create(SignUpService::class.java)
    val userIdCheckService : UserIdCheckService = retrofit.create(UserIdCheckService::class.java)
    val userNicknameCheckService : UserNicknameCheckService = retrofit.create(UserNicknameCheckService::class.java)
    val smsService : SMSService = retrofit.create(SMSService::class.java)
    val myFileService: MyFileService = retrofit.create(MyFileService::class.java)
    val planService : PlanService = retrofit.create(PlanService::class.java)
    val homeDiaryService : HomeDiaryService = retrofit.create(HomeDiaryService::class.java)
}