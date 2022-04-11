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



        setSupportActionBar(signuping_toolbar)//커스텀한 툴바 액션바로 use
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //액션바에 표시되는 제목의 표시유무 결정 false로 해야 툴바 이름 화면에 보임
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_btn)

        button5.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sign_uping_menu, menu)       // sign uping menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}
