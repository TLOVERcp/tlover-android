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
        //아이디 edittext
        binding.createAccountIdEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.createAccountIdEdittext.text.toString() != "") {
                    binding.createAccountCheckRepetitionButton.setBackgroundResource(R.drawable.confirm_repetition_btn_background_clicked)
                    binding.createAccountCheckRepetitionButton.setTextColor(Color.WHITE)
                }
                else {
                    binding.createAccountCheckRepetitionButton.setBackgroundResource(R.drawable.confirm_repetition_btn_background)
                    binding.createAccountCheckRepetitionButton.setTextColor(Color.parseColor("#3F3F45"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //nickname eidttext
        binding.createAccountNicknameEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.createAccountNicknameEdittext.text.toString() != "") {
                    binding.createAccountCheckRepetitionButtonNickname.setBackgroundResource(R.drawable.confirm_repetition_btn_background_clicked)
                    binding.createAccountCheckRepetitionButtonNickname.setTextColor(Color.WHITE)
                }
                else {
                    binding.createAccountCheckRepetitionButtonNickname.setBackgroundResource(R.drawable.confirm_repetition_btn_background)
                    binding.createAccountCheckRepetitionButtonNickname.setTextColor(Color.parseColor("#3F3F45"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }


}