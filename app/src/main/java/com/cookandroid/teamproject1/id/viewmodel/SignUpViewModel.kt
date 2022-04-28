package com.cookandroid.teamproject1.id.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  회원가입과 관련한 LiveData를 다루는 ViewModel 페이지
 *  작성자 : 윤성식
 *  SharedLiveData를 통한 뷰간 데이터 전달
 */

class SignUpViewModel : ViewModel() {

    companion object{
        const val TAG: String = "로그"
    }
    private val _currentInputId = MutableLiveData<String>("")
    val currentInputId: LiveData<String> = _currentInputId

    private val _currentInputPw = MutableLiveData<String>("")
    val currentInputPw: LiveData<String> = _currentInputPw

    private val _currentInputPhone = MutableLiveData<String>("")
    val currentInputPhone: LiveData<String> = _currentInputPhone

    private val _currentInputNickname = MutableLiveData<String>("")
    val currentInputNickname: LiveData<String> = _currentInputNickname

    fun updateInputId(input: String){
        _currentInputId.value = input
    }

    fun getA(): String? {
        return _currentInputId.value
    }
    fun getb(): String? {
        return _currentInputPhone.value
    }
    fun updateInputPw(input: String){
        _currentInputPw.value = input
    }

    fun updateInputPhone(input: String){
        _currentInputPhone.value = input
//        println(_currentInputPhone.value)
    }

    fun updateInputNickname(input: String){
        _currentInputNickname.value = input
    }
}