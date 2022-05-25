package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.diary.model.ResponseDiaryViewData
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * diary 상세보기 프래그먼트
 */
class DiaryViewFragment : Fragment(){
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
        val args : DiaryViewFragmentArgs by navArgs()
        val diaryId = args.diaryId
        Log.d(SignUpViewModel.TAG, "onViewCreated: $diaryId")

        val call: Call<ResponseDiaryViewData> = ServiceCreator.diaryService.getDiaryDetail(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            diaryId
        )

        call.enqueue(object : Callback<ResponseDiaryViewData> {
            override fun onResponse(
                call: Call<ResponseDiaryViewData>,
                response: Response<ResponseDiaryViewData>
            ) {
                if (response.code() == 200) {
                    Log.e("reponse!!!!!!!", "200~!!gg")
                    mBinding?.diaryViewList = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseDiaryViewData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        super.onViewCreated(view, savedInstanceState)
    }
}