package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentPlanAuthListBinding
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.model.ResponsePlanAuthData
import com.cookandroid.teamproject1.plan.view.adapter.PlanAuthRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *  계획 권한 요청받은 리스트 목록 프래그먼트
 */
class PlanAuthListFragment : Fragment() {
    private var mBinding : FragmentPlanAuthListBinding?= null
    private var planAuthRVAdapter = PlanAuthRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanAuthListBinding.inflate(inflater, container, false)
        mBinding = binding


        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //뒤로가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener{
            it.findNavController().navigate(R.id.action_planAuthListFragment_to_homeFragment)
        }

        mBinding?.fragmentPlanAuthRv?.adapter = planAuthRVAdapter
        /**
         * 0515 계획 권한 요청받은 목록 조회 api
         * 작성자 : 윤성식
         */
        val call: Call<ResponsePlanAuthData> = ServiceCreator.planService.getPlanAuth(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt()
        )
        call.enqueue(object: Callback<ResponsePlanAuthData> {
            override fun onResponse(
                call: Call<ResponsePlanAuthData>,
                responsePlan: Response<ResponsePlanAuthData>
            ) {

                if (responsePlan.code() == 200){
//                    Log.e("response", "200success")
                    val body = responsePlan.body()
                    if (body != null) {
                        val data = body.data
                        println(data)
                        planAuthRVAdapter.planAuthList = data
                    }
                    planAuthRVAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<ResponsePlanAuthData>, t: Throwable) {
                Log.e("response", "fail")

            }

        })

    }
}