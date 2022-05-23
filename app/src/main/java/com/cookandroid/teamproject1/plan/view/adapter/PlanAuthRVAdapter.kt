package com.cookandroid.teamproject1.plan.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.ItemHomeDiaryBinding
import com.cookandroid.teamproject1.databinding.ItemPlanAuthListBinding
import com.cookandroid.teamproject1.diary.view.fragment.DiaryFragmentDirections
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.model.ResponseAcceptAuthData
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 권한 요청 받은 리사이클러뷰 어댑터클래스
 */
class PlanAuthRVAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var planAuthList = mutableListOf<ResponsePlanAuthData.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemPlanAuthListBinding = ItemPlanAuthListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(planAuthList[position])
    }

    override fun getItemCount(): Int = planAuthList.size

    inner class ViewHolder(
        private val binding: ItemPlanAuthListBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(planAuthDataList: ResponsePlanAuthData.Result){
            binding.authPlanItem = planAuthDataList
            /**
             * 0515 계획 권한 요청 받은 목록에서 수락버튼을 클릭했을 때 api (수락 api)
             * 작성자 : 윤성식
             */
            binding.itemAuthAcceptBt.setOnClickListener{
                val call: Call<ResponseAcceptAuthData> = ServiceCreator.planService.acceptPlanAuth(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    planAuthDataList.authorityPlanId
                )
                call.enqueue(object: Callback<ResponseAcceptAuthData> {
                    override fun onResponse(
                        call: Call<ResponseAcceptAuthData>,
                        response: Response<ResponseAcceptAuthData>
                    ) {
                        if(response.code() == 200){
                            Log.e("권한요청성공", "200!!~~~")
                        }
                    }

                    override fun onFailure(call: Call<ResponseAcceptAuthData>, t: Throwable) {
                        Log.d(SignUpViewModel.TAG, "onFailure: $t")
                    }
                })

            }

            /**
             * 0523 계획 권한 요청 받은 목록에서 거절버튼을 클릭했을 때 api (거절 api)
             * 작성자 : 윤성식
             */
            binding.itemAuthRejectBt.setOnClickListener{
                val call: Call<ResponseAcceptAuthData> = ServiceCreator.planService.rejectPlanAuth(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    planAuthDataList.authorityPlanId
                )
                call.enqueue(object: Callback<ResponseAcceptAuthData> {
                    override fun onResponse(
                        call: Call<ResponseAcceptAuthData>,
                        response: Response<ResponseAcceptAuthData>
                    ) {
                        if(response.code() == 200){
                            Log.e("거절하기", "200!!~~~")
                        }
                    }

                    override fun onFailure(call: Call<ResponseAcceptAuthData>, t: Throwable) {
                        Log.d(SignUpViewModel.TAG, "onFailure: $t")
                    }
                })
            }

        }
    }

}