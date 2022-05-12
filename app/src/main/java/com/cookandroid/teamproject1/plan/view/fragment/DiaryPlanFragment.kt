package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryPlanBinding
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.view.adapter.DiaryPlanRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * diary plan view
 * 다이어리 계획 모음
 */
class DiaryPlanFragment : Fragment() {

    private var mBinding : FragmentDiaryPlanBinding?=null
    private var diaryPlanRVAdapter = DiaryPlanRVAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryPlanBinding.inflate(inflater, container, false)
        mBinding = binding
        mBinding?.fragmentDiaryPlanRandomRv?.adapter = diaryPlanRVAdapter

        val call: Call<ResponseDiaryPlanData> = ServiceCreator.planService.getDiaryPlan(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt()

        )


        call.enqueue(object: Callback<ResponseDiaryPlanData> {
            override fun onResponse(
                call: Call<ResponseDiaryPlanData>,
                responsePlan: Response<ResponseDiaryPlanData>
            ) {


                if (responsePlan.code() == 200){
//                    Log.e("response", "200success")
                    val body = responsePlan.body()
                    if (body != null) {
                        val data = body.data
                        println(data)
                        diaryPlanRVAdapter.diaryPlanList = data
                    }
                    diaryPlanRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseDiaryPlanData>, t: Throwable) {
                Log.e("response", "fail")

            }

        })


        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}