package com.cookandroid.teamproject1.id.view.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import com.cookandroid.teamproject1.FirstTitleActivity
import com.cookandroid.teamproject1.HomeActivity
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.id.model.RequestLoginData
import com.cookandroid.teamproject1.id.model.ResponseLoginData
import com.cookandroid.teamproject1.plan.view.fragment.DiaryPlanFragment
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_activity)
//        처음에 들어갔을 때, 유저 id와 유저 pw가 남아있으면 홈액티비티로,
//        그게 아니면 로그인화면으로
        val intentLoginSuccess = Intent(this, HomeActivity::class.java)
        var intentLoginFail =Intent(this, FirstTitleActivity::class.java)
        //xml 소스 연결
        if(TloverApplication.prefs.getUserId().isNotEmpty() and TloverApplication.prefs.getUserPW().isNotEmpty()){

            val requestLoginData = RequestLoginData(
                userId = TloverApplication.prefs.getUserId(),
                userPw = TloverApplication.prefs.getUserPW()
            )

            val call: Call<ResponseLoginData> = ServiceCreator.signInService.postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if(response.code() == 200){
                        TloverApplication.prefs.setString("jwt", response.body()?.jwt.toString())
                        TloverApplication.prefs.setString("message", response.body()?.message.toString())
                        TloverApplication.prefs.setString("refreshToken", response.body()?.refreshToken.toString())
                        TloverApplication.prefs.setString("userNickname", response.body()?.userNickname.toString())
                        TloverApplication.prefs.setString("userRating", response.body()?.userRating.toString())
                        TloverApplication.prefs.setString("userThemaName", response.body()?.userThemaName.toString())
                        TloverApplication.prefs.setString("userRegionName", response.body()?.userRegionName.toString())

                        var handler = Handler()
                        handler.postDelayed({
                            startActivity(intentLoginSuccess)
                        }, 2000)
                    }
                    else{
                        var handler = Handler()
                        handler.postDelayed({
                            startActivity(intentLoginFail)
                        }, 2000)
                    }
                }

                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    var handler = Handler()
                    handler.postDelayed({
                        startActivity(intentLoginFail)
                    }, 2000)
                }

            })
        }
        else{
            startActivity(intentLoginFail)
        }
//        var handler=Handler()
//        handler.postDelayed({
//            var intent=Intent(this, FirstTitleActivity::class.java)
//           startActivity(intent)// 다음 화면으로 넘어가기
//      },2000)//2초뒤 실행

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
