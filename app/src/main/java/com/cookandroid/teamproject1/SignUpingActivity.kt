package com.cookandroid.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import com.cookandroid.teamproject1.databinding.SignInBinding
import com.cookandroid.teamproject1.databinding.SignUpBinding
import kotlinx.android.synthetic.main.sign_uping.*
import com.cookandroid.teamproject1.databinding.SignUpingBinding

class SignUpingActivity : AppCompatActivity() {

    lateinit var sbinding: SignUpingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_uping)

        button5.setOnClickListener {
            startActivity(Intent(this,CreateAccountActivity::class.java))
        }
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
