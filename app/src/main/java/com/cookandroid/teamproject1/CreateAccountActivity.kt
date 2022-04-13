package com.cookandroid.teamproject1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.CreateAccountBinding
import java.util.regex.Pattern

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

        //비밀번호 8~20글자 대소문자 특수문자 확인
        binding.createAccountPasswordEdittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.createAccountConfirmButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    binding.createAccountConfirmButton.isEnabled = true
                    binding.createAccountPasswordCheckimage.visibility = View.VISIBLE  //조건 맞으면 체크이미지 활성화
                    binding.createAccountPasswordWarningTextview.visibility = View.GONE //맞으면 경고메세지 지워짐  밑의 경우도 마찬가지
                }
                else{
                    binding.createAccountPasswordCheckimage.visibility = View.GONE
                    binding.createAccountPasswordWarningTextview.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    if(binding.createAccountPasswordcheckEdittext.text.toString() == binding.createAccountPasswordEdittext.text.toString()) {
                        binding.createAccountConfirmButton.isEnabled = true
                    }
                }
            }
        })

        //비밀번호 확인과 일치 여부 판단
        binding.createAccountPasswordcheckEdittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.createAccountConfirmButton.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    if(binding.createAccountPasswordcheckEdittext.text.toString() == binding.createAccountPasswordEdittext.text.toString()) {
                        binding.createAccountConfirmButton.isEnabled = true
                        binding.createAccountPasswordcheckCheckimage.visibility = View.VISIBLE
                        binding.createAccountPasswordCheckWarningTextview.visibility = View.GONE
                    }
                    else{
                        binding.createAccountPasswordcheckCheckimage.visibility = View.GONE
                        binding.createAccountPasswordCheckWarningTextview.visibility = View.VISIBLE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    if(binding.createAccountPasswordcheckEdittext.text.toString() == binding.createAccountPasswordEdittext.text.toString())
                        binding.createAccountConfirmButton.isEnabled = true
                }
            }
        })

    }


}