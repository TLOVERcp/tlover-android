package com.cookandroid.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import com.cookandroid.teamproject1.databinding.SignInBinding
import com.cookandroid.teamproject1.databinding.SignUpBinding
import kotlinx.android.synthetic.main.sign_uping.*

class SignUpingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_uping)

        //임시로 설정(수정 요망)
        button5.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
    }

}
