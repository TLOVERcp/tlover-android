package com.cookandroid.teamproject1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.SignInBinding


class SignInActivity : AppCompatActivity() {

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
                    binding.signinFindid.visibility=View.VISIBLE
                }
                else {
                    isIdEntered = false
                    changeConfirmButtonColor()
                    binding.signinFindid.visibility=View.GONE
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
                    binding.signinPwnot.visibility=View.VISIBLE
                }
                else {
                    isPasswordEntered = false
                    changeConfirmButtonColor()
                    binding.signinPwnot.visibility=View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //뒤로가기 버튼
        binding.signInBackbtn.setOnClickListener {
            finish()
        }

        //아이디 텍스트 필드 포커스된 경우
        binding.signinId.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.signinId.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.signinId.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //비밀번호 텍스트 필드 포커스된 경우
        binding.signinPw.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.signinPw.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.signinPw.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })
    }
    //로그인 버튼 색 바꾸는 함수
    fun changeConfirmButtonColor() {
        if (isIdEntered && isPasswordEntered) {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
            binding.signinLoginBtn.setTextColor(Color.WHITE)
            binding.signinLoginBtn.isEnabled = true
        }
        else {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.confirm_btn_background)
            binding.signinLoginBtn.setTextColor(Color.parseColor("#6E6E76"))
            binding.signinLoginBtn.isEnabled = false
        }
    }


}
