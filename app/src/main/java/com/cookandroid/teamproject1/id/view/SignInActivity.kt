package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.teamproject1.*
import com.cookandroid.teamproject1.databinding.SignInBinding
import com.cookandroid.teamproject1.id.model.RequestLoginData
import com.cookandroid.teamproject1.id.model.ResponseLoginData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    var isIdEntered : Boolean = false
    var isPasswordEntered : Boolean = false

    lateinit var binding: SignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LoginSuccessActivity::class.java)

        //id edittext 비어있는 경우 체크
        binding.signinId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.signinId.text.toString() != "") {
                    isIdEntered = true
                    changeConfirmButtonColor()
//                    binding.signinFindid.visibility=View.VISIBLE
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
//                    binding.signinPwnot.visibility=View.VISIBLE
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
        binding.signInBackImg.setOnClickListener {
            startActivity(Intent(this, FirstTitleActivity::class.java))
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
        //로그인 버튼 클릭 시
        /**
         * 작성자 : 윤성식, 이충환
         * 로그인 버튼 클릭 시 코드 반환 및 로그인 여부 반환
         */
        binding.signinLoginBtn.setOnClickListener {
            val inputId = binding.signinId.text.toString()
            val inputPassword = binding.signinPw.text.toString()

            val requestLoginData = RequestLoginData(
                userId = inputId,
                userPw = inputPassword,
            )
            val call: Call<ResponseLoginData> = ServiceCreator.signInService.postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData>{
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    if(response.code() == 200){
                        TloverApplication.prefs.setString("jwt", response.body()?.jwt.toString())
                        TloverApplication.prefs.setString("message", response.body()?.message.toString())
                        TloverApplication.prefs.setString("refreshToken", response.body()?.refreshToken.toString())
                        TloverApplication.prefs.setString("userNickname", response.body()?.userNickname.toString())
//                        println(TloverApplication.prefs.getString("userNickname", "null"))
                        TloverApplication.prefs.setUserId(inputId)
                        TloverApplication.prefs.setUserPW(inputPassword)
//                        println(response.body()?.jwt)
                        startActivity(intent)
                    }
                    else{
                        binding.signinFindid.visibility=View.VISIBLE
                        binding.signinPwnot.visibility=View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                    Log.e("login_server_test", "fail")
                }
            })



        }
    }

    //로그인 버튼 색 바꾸는 함수
    fun changeConfirmButtonColor() {
        if (isIdEntered && isPasswordEntered) {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
            binding.signinLoginBtn.setTextColor(Color.WHITE)
            binding.signinLoginBtn.isEnabled = true
        }
        else {
            binding.signinLoginBtn.setBackgroundResource(R.drawable.certification_requ)
            binding.signinLoginBtn.setTextColor(Color.parseColor("#6E6E76"))
            binding.signinLoginBtn.isEnabled = false
        }


    }



}
