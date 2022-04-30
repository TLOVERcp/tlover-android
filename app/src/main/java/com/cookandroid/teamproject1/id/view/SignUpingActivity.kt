package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cookandroid.teamproject1.FirstTitleActivity
import com.cookandroid.teamproject1.R

import com.cookandroid.teamproject1.databinding.SignUpingBinding
import com.cookandroid.teamproject1.id.model.RequestSMSData
import com.cookandroid.teamproject1.id.model.ResponseSMSData
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.util.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class SignUpingActivity : AppCompatActivity() {

    var isTime : Boolean = false
    var isquoteNum: Boolean = false
    private lateinit var sharedViewModel : SignUpViewModel

    lateinit var certificationCode: String

    lateinit var sbinding: SignUpingBinding

    private val mCountDown: CountDownTimer = object : CountDownTimer(180000,500) {
        override fun onTick(p0: Long) {
            sbinding.textTimer.text = "${(p0 / (1000 * 60) % 60).toString()+":"+((p0 / 1000) % 60).toString()}"
            isTime = true
        }

        override fun onFinish() {
            isTime = false
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        sbinding = SignUpingBinding.inflate(layoutInflater)
        setContentView(sbinding.root)

        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        sbinding.btnConfirm.setOnClickListener {
            if(sbinding.signTextCtf.text.toString()==certificationCode && sbinding.signTextCtf.text.length==5){
//                sharedViewModel.updateInputPhone(sbinding.signupingPnum.text.toString())
                // 고객 핸드폰 번호
                val intent = Intent(this, CreateAccountActivity::class.java)
                intent.putExtra("pnum", sbinding.signupingPnum.text.toString())
                startActivity(intent)
            }
            else{
                sbinding.signUpMsgctf.visibility = View.VISIBLE
            }

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
                    sbinding.btnCtf.setBackgroundResource(R.drawable.certification_requ)
                    sbinding.btnCtf.setTextColor(Color.parseColor("#6E6E76"))
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //확인 버튼 클릭시 새로운 필드와 버튼 출력
        /**
         * SMS문자인증 api 연결
         * 작성자 : 윤성식
         */
        sbinding.btnCtf.setOnClickListener{
            sbinding.btnCtf.visibility=View.INVISIBLE
            sbinding.btnConfirm.visibility= View.VISIBLE
            sbinding.btnTransport.visibility = View.VISIBLE
            sbinding.signTextCtf.visibility = View.VISIBLE
            sbinding.signupingCtfnumber.visibility = View.VISIBLE
            sbinding.textTimer.visibility = View.VISIBLE
            mCountDown.start()

            val toastView = layoutInflater.inflate(R.layout.custom_toast,null)

            toastView.run {

            }
            val t2 = Toast(this)
            t2.view = toastView
            t2.show()
            t2.setGravity(Gravity.BOTTOM,0,0)
            t2.duration = Toast.LENGTH_SHORT

            val requestSMSData = RequestSMSData(
                phoneNum = sbinding.signupingPnum.text.toString()
            )

            val call: Call<ResponseSMSData> = ServiceCreator.smsService.postSendSMS(requestSMSData)

            call.enqueue(object: Callback<ResponseSMSData>{
                override fun onResponse(
                    call: Call<ResponseSMSData>,
                    response: Response<ResponseSMSData>
                ) {
                    if(response.code()==200){
                        certificationCode = response.body()?.certifyvalue.toString()
//                        println(certificationCode)
//                        println(sharedViewModel.currentInputPhone.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseSMSData>, t: Throwable) {
                    Log.e("sms_server_test", "fail")
                }

            })
        }

        //인증번호 입력 필드(버튼 색 변경 및 경고 메세지 출력)
        sbinding.signTextCtf.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(sbinding.signTextCtf.text.toString() != "") {
                    sbinding.btnTransport.setBackgroundResource(R.drawable.confirm_repetition_btn_background_clicked)
                    sbinding.btnTransport.setTextColor(Color.WHITE)
                    sbinding.btnTransport.isEnabled = true
                }


                if(Pattern.matches("^\\d{5}\$", sbinding.signTextCtf.text.toString())) {
//                    sbinding.signUpMsgctf.visibility = View.VISIBLE
                    isquoteNum = true
                    changeConfirmButton()
                }
                else {
                    isquoteNum= false
                    changeConfirmButton()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //전화번호 텍스트 필드 포커스된 경우
        sbinding.signupingPnum.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    sbinding.signupingPnum.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    sbinding.signupingPnum.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //인증번호 텍스트 필드 포커스된 경우
        sbinding.signTextCtf.setOnFocusChangeListener (object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                if (p1) {
                    sbinding.signTextCtf.setBackgroundResource(R.drawable.phone_requ_selected)
                }
                else {
                    sbinding.signTextCtf.setBackgroundResource(R.drawable.phone_requ)
                }
            }

        })

        //뒤로가기 버튼
        sbinding.signUpingBackImg.setOnClickListener {
            startActivity(Intent(this, FirstTitleActivity::class.java))
        }

        //재전송 버튼
        sbinding.btnTransport.setOnClickListener {
            mCountDown.start()
            val toastView = layoutInflater.inflate(R.layout.custom_toast,null)

            toastView.run {

            }
            val t2 = Toast(this)
            t2.view = toastView
            t2.show()
            t2.setGravity(Gravity.BOTTOM,0,0)
            t2.duration = Toast.LENGTH_SHORT
        }
    }
        private fun changeConfirmButton() {
            if(isTime && isquoteNum) {
                sbinding.btnConfirm.isEnabled = true
                sbinding.btnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
                sbinding.btnConfirm.setTextColor(Color.WHITE)
            }
            else {
                sbinding.btnConfirm.isEnabled = false
                sbinding.btnConfirm.setBackgroundResource(R.drawable.certification_requ)
                sbinding.btnConfirm.setTextColor(Color.parseColor("#6E6E76"))
            }
        }



}
