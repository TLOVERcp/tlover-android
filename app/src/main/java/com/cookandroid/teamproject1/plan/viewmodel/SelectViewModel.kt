package com.cookandroid.teamproject1.plan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectViewModel : ViewModel() {

    companion object{
        const val TAG: String = "로그"
    }
    private val _currentRegion = MutableLiveData<String>("")
    val currentInputRegion: LiveData<String> = _currentRegion

    fun updateInputRegion(input: String){
        _currentRegion.value = input
    }

    fun getA(): String? {
        return _currentRegion.value
    }

}