package com.cookandroid.teamproject1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.CreateAccountBinding
import kotlinx.android.synthetic.main.create_account.*

class CreateAccountActivity : AppCompatActivity() {

    lateinit var binding: CreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //아이디 중복확인 버튼
        binding.createAccountCheckRepetitionButton.setOnClickListener {
            binding.createAccountIdWarningTextview.visibility = View.VISIBLE
            binding.createAccountIdCheckimage.visibility = View.VISIBLE
        }
        //닉네임 중복확인 버튼
        binding.createAccountCheckRepetitionButtonNickname.setOnClickListener {
            binding.createAccountNicknameWarningTextview.visibility = View.VISIBLE
            binding.createAccountNicknameCheckimage.visibility = View.VISIBLE
        }

    }


}