package com.cookandroid.teamproject1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.databinding.CreateAccountBinding
import java.util.regex.Pattern

class CreateAccountActivity : AppCompatActivity() {

    var isId : Boolean = false
    var isPassword : Boolean = false
    var isPasswordCheck1 : Boolean = false
    var isPasswordCheck2 : Boolean = false
    var isNickname : Boolean = false

    lateinit var binding: CreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //아이디 중복확인 버튼
        binding.createAccountCheckRepetitionButton.setOnClickListener {
            binding.createAccountIdCheckimage.bringToFront()
            binding.createAccountIdWarningTextview.visibility = View.VISIBLE
            binding.createAccountIdCheckimage.visibility = View.VISIBLE
            isId = true
            changeConfirmButtonColor()

        }

        //닉네임 중복확인 버튼
        binding.createAccountCheckRepetitionButtonNickname.setOnClickListener {
            binding.createAccountNicknameWarningTextview.visibility = View.VISIBLE
            binding.createAccountNicknameCheckimage.visibility = View.VISIBLE
            binding.createAccountNicknameCheckimage.bringToFront()
            isNickname = true
            changeConfirmButtonColor()
        }
        //아이디 edittext
        binding.createAccountIdEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.createAccountIdEdittext.text.toString() != "") {
                    binding.createAccountCheckRepetitionButton.setBackgroundResource(R.drawable.confirm_repetition_btn_background_clicked)
                    binding.createAccountCheckRepetitionButton.setTextColor(Color.WHITE)
                    binding.createAccountCheckRepetitionButton.isEnabled = true
                    changeConfirmButtonColor()
                }
                else {
                    binding.createAccountCheckRepetitionButton.setBackgroundResource(R.drawable.confirm_repetition_btn_background)
                    binding.createAccountCheckRepetitionButton.setTextColor(Color.parseColor("#3F3F45"))
                    binding.createAccountCheckRepetitionButton.isEnabled = false
                    changeConfirmButtonColor()
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
                    binding.createAccountCheckRepetitionButtonNickname.isEnabled = true
                    isPassword = true
                    changeConfirmButtonColor()
                }
                else {
                    binding.createAccountCheckRepetitionButtonNickname.setBackgroundResource(R.drawable.confirm_repetition_btn_background)
                    binding.createAccountCheckRepetitionButtonNickname.setTextColor(Color.parseColor("#3F3F45"))
                    binding.createAccountCheckRepetitionButtonNickname.isEnabled = false
                    isPassword = false
                    changeConfirmButtonColor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //비밀번호 8~20글자 대소문자 특수문자 확인
        binding.createAccountPasswordEdittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {

                    binding.createAccountPasswordCheckimage.visibility = View.VISIBLE  //조건 맞으면 체크이미지 활성화
                    binding.createAccountPasswordCheckimage.bringToFront()
                    binding.createAccountPasswordWarningTextview.visibility = View.GONE //맞으면 경고메세지 지워짐  밑의 경우도 마찬가지
                    isPasswordCheck1 = true
                    changeConfirmButtonColor()
                }
                else{
                    binding.createAccountPasswordCheckimage.visibility = View.GONE
                    binding.createAccountPasswordWarningTextview.visibility = View.VISIBLE
                    isPasswordCheck1 = false
                    changeConfirmButtonColor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    if(binding.createAccountPasswordcheckEdittext.text.toString() == binding.createAccountPasswordEdittext.text.toString()) {

                    }
                }
            }
        })

        //비밀번호 확인과 일치 여부 판단
        binding.createAccountPasswordcheckEdittext.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {
                    if(binding.createAccountPasswordcheckEdittext.text.toString() == binding.createAccountPasswordEdittext.text.toString()) {

                        binding.createAccountPasswordcheckCheckimage.visibility = View.VISIBLE
                        binding.createAccountPasswordcheckCheckimage.bringToFront()
                        binding.createAccountPasswordCheckWarningTextview.visibility = View.GONE
                        isPasswordCheck2 = true
                        changeConfirmButtonColor()

                    }
                    else{
                        binding.createAccountPasswordcheckCheckimage.visibility = View.GONE
                        binding.createAccountPasswordCheckWarningTextview.visibility = View.VISIBLE
                        isPasswordCheck2 = false
                        changeConfirmButtonColor()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.createAccountPasswordEdittext.text.toString())) {

                }
            }
        })

        //회원가입 완료시 홈액티비티로 이동
        binding.createAccountConfirmButton.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        //아이디 텍스트 필드 포커스된 경우
        binding.createAccountIdEdittext.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.createAccountIdEdittext.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.createAccountIdEdittext.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //비밀번호 텍스트 필드 포커스된 경우
        binding.createAccountPasswordEdittext.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.createAccountPasswordEdittext.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.createAccountPasswordEdittext.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //비밀번호확인 텍스트 필드 포커스된 경우
        binding.createAccountPasswordcheckEdittext.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.createAccountPasswordcheckEdittext.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.createAccountPasswordcheckEdittext.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //닉네임 텍스트 필드 포커스된 경우
        binding.createAccountNicknameEdittext.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    binding.createAccountNicknameEdittext.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    binding.createAccountNicknameEdittext.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        binding.createAccountBackImg.setOnClickListener {
            startActivity(Intent(this,SignUpingActivity::class.java))
        }

    }

    private fun changeConfirmButtonColor() {
        if (isId && isPassword && isPasswordCheck1 && isPasswordCheck2 && isNickname) {
            binding.createAccountConfirmButton.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
            binding.createAccountConfirmButton.setTextColor(Color.WHITE)
            binding.createAccountConfirmButton.isEnabled = true
        }
        else {
            binding.createAccountConfirmButton.setBackgroundResource(R.drawable.certification_requ)
            binding.createAccountConfirmButton.setTextColor(Color.parseColor("#6E6E76"))
            binding.createAccountConfirmButton.isEnabled = false
        }
    }


}