package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.diary.view.fragment.DiaryViewFragmentArgs
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.model.ResponseDiaryViewData
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiaryViewFragment : Fragment() {
    private var mBinding : FragmentDiaryViewBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryViewBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: DiaryViewFragmentArgs by navArgs()
        val diaryId = args.diaryId
        Log.d(SignUpViewModel.TAG, "onViewCreated: $diaryId")

        val call:Call<ResponseDiaryViewData> = ServiceCreator.planService.getDiaryView(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            diaryId.toInt()
        )

        call.enqueue(object: Callback<ResponseDiaryViewData>{
            override fun onResponse(
                call: Call<ResponseDiaryViewData>,
                response: Response<ResponseDiaryViewData>
            ) {
                if(response.code() == 200){
                    Log.e("reponse", "200")
                    mBinding?.diaryDetailView = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseDiaryViewData>, t: Throwable) {
                Log.d(SignUpViewModel.TAG, "onFailure: $t")
            }

        })


    }
}