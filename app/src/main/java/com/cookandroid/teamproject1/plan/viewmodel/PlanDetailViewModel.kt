package com.cookandroid.teamproject1.plan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 계획 상세보기 테스트
 */
class PlanDetailViewModel : ViewModel() {
    private val _currentPlanId = MutableLiveData<Int>()
    val currentPlanId : LiveData<Int> = _currentPlanId

    private val _currentPlanStartDate = MutableLiveData<String>()
    val currentPlanStartDate : LiveData<String> = _currentPlanStartDate

    fun updatePlanId(planId : Int?){
        _currentPlanId.value = planId!!
    }

    fun updatePlanStartDate(planStartDate : String?){
        _currentPlanStartDate.value = planStartDate!!
    }

}