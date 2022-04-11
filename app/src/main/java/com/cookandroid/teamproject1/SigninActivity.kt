package com.cookandroid.teamproject1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.SignInBinding


class SigninActivity : AppCompatActivity() {

    var isIdEntered : Boolean = false
    var isPasswordEntered : Boolean = false

    lateinit var binding: SignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //id edittext 비어있는 경우 체크
        binding.signinId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signinId.text.toString() != "") {
                    isIdEntered = true
                    changeConfirmButtonColor()
                }
                else {
                    isIdEntered = false
                    changeConfirmButtonColor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //비밀번호 edittext 비어있는 경우 체크
        binding.signinPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signinPw.text.toString() != "") {
                    isPasswordEntered = true
                    changeConfirmButtonColor()
                }
                else {
                    isPasswordEntered = false
                    changeConfirmButtonColor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }
    //로그인 버튼 색 바꾸는 함수
    fun changeConfirmButtonColor() {
        if (isIdEntered && isPasswordEntered) {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
            binding.signinLoginBtn.setTextColor(Color.WHITE)
        }
        else {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.confirm_btn_background)
            binding.signinLoginBtn.setTextColor(Color.parseColor("#6E6E76"))
        }
    }

}
