package com.cookandroid.teamproject1.id.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import com.cookandroid.teamproject1.FirstTitleActivity
import com.cookandroid.teamproject1.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_activity)
        //xml 소스 연결

      var handler=Handler()
        handler.postDelayed({
            var intent=Intent(this, FirstTitleActivity::class.java)
            startActivity(intent)// 다음 화면으로 넘어가기
        },2000)//2초뒤 실행
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
