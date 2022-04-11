package com.cookandroid.teamproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cookandroid.teamproject1.databinding.SignUpingBinding

class SignUpingActivity : AppCompatActivity() {

    lateinit var sbinding: SignUpingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sbinding = SignUpingBinding.inflate(layoutInflater)
        setContentView(sbinding.root)

        sbinding.btnCtf.setOnClickListener{
            sbinding.btnCtf.visibility=View.INVISIBLE
            sbinding.btnConfirm.visibility= View.VISIBLE
            sbinding.btnTransport.visibility = View.VISIBLE
            sbinding.signTextCtf.visibility = View.VISIBLE
            sbinding.signUpMsgctf.visibility = View.VISIBLE
            sbinding.signUpingCtfnumber.visibility = View.VISIBLE
            sbinding.textTimer.visibility = View.VISIBLE

        }





    }




}
