package com.cookandroid.teamproject1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_activity)
        //xml 소스 연결

      var handler=Handler()
        handler.postDelayed({
            var intent=Intent(this,SignUpingActivity::class.java)
            startActivity(intent)// 다음 화면으로 넘어가기
        },2000)//2초뒤 실행
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
