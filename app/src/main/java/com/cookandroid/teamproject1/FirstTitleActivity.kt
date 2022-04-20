package com.cookandroid.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.teamproject1.databinding.FirstTitleBinding
import com.cookandroid.teamproject1.id.SignInActivity

class FirstTitleActivity : AppCompatActivity() {

    lateinit var binding: FirstTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = FirstTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textSignup.setOnClickListener {
            startActivity(Intent(this,SignUpingActivity::class.java))
        }

        binding.textSignin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
//임시로 여행지 선택창 걸어놓음 0416 김도영
        binding.textQuest.setOnClickListener {
            startActivity(Intent(this,SelectDestActivity::class.java))
        }

    }
}