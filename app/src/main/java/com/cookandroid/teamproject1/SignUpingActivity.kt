package com.cookandroid.teamproject1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import android.view.View

import com.cookandroid.teamproject1.databinding.SignUpingBinding
import java.util.regex.Pattern

class SignUpingActivity : AppCompatActivity() {

    lateinit var sbinding: SignUpingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        sbinding = SignUpingBinding.inflate(layoutInflater)
        setContentView(sbinding.root)

        super.onCreate(savedInstanceState)



        sbinding.btnConfirm.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }

        //전화번호 입력확인
        sbinding.signupingPnum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                sbinding.btnCtf.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Pattern.matches("^\\d{11}\$", sbinding.signupingPnum.text.toString())) {
                    sbinding.btnCtf.isEnabled = true
                    sbinding.btnCtf.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
                    sbinding.btnCtf.setTextColor(Color.WHITE)
                }
                else {
                    sbinding.btnCtf.setBackgroundResource(R.drawable.confirm_btn_background)
                    sbinding.btnCtf.setTextColor(Color.parseColor("#ADB5BD"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //확인 버튼 클릭시 새로운 필드와 버튼 출력
        sbinding.btnCtf.setOnClickListener{
            sbinding.btnCtf.visibility=View.INVISIBLE
            sbinding.btnConfirm.visibility= View.VISIBLE
            sbinding.btnTransport.visibility = View.VISIBLE
            sbinding.signTextCtf.visibility = View.VISIBLE
            sbinding.signUpingCtfnumber.visibility = View.VISIBLE
            sbinding.textTimer.visibility = View.VISIBLE
        }

        //인증번호 입력 필드(버튼 색 변경 및 경고 메세지 출력)
        sbinding.signTextCtf.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(Pattern.matches("^\\d{4}\$", sbinding.signTextCtf.text.toString())) {
                    sbinding.signUpMsgctf.visibility = View.VISIBLE
                    sbinding.btnConfirm.isEnabled = true
                    sbinding.btnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
                    sbinding.btnConfirm.setTextColor(Color.WHITE)
                }
                else {
                    sbinding.btnConfirm.isEnabled = false
                    sbinding.btnConfirm.setBackgroundResource(R.drawable.confirm_btn_background)
                    sbinding.btnConfirm.setTextColor(Color.parseColor("#ADB5BD"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}
