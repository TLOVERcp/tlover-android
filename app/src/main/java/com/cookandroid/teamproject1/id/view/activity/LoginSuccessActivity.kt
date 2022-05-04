package com.cookandroid.teamproject1.id.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import android.os.Looper
import com.cookandroid.teamproject1.HomeActivity
import com.cookandroid.teamproject1.R

class LoginSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_success)
        //xml 소스 연결

      var handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
        },1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
