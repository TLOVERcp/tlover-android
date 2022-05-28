package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryLibraryBinding
import com.cookandroid.teamproject1.diary.model.ResponseMyDiaryData
import com.cookandroid.teamproject1.diary.view.adapter.DiaryLibraryRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * diary library view
 * 여행 계획, 다이어리 모두 작성한 것들 모음 화면
 */
class DiaryLibraryFragment : Fragment(){
    private var mBinding : FragmentDiaryLibraryBinding?= null
    private var diaryLibraryRVAdapter = DiaryLibraryRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryLibraryBinding.inflate(inflater, container, false)
        mBinding = binding

        mBinding?.fragmentDiaryLibraryRandomRv?.adapter = diaryLibraryRVAdapter

        val call: Call<ResponseMyDiaryData> = ServiceCreator.diaryService.getMyDiary(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt()
        )

        call.enqueue(object : Callback<ResponseMyDiaryData>{
            override fun onResponse(
                call: Call<ResponseMyDiaryData>,
                response: Response<ResponseMyDiaryData>
            ) {
                if(response.code() == 200){
                    Log.e("reponse!!!!!!!", "200~!!gg")
                    val body = response.body()?.data
                    if(body != null){
                        diaryLibraryRVAdapter.diaryLibraryList = body
                    }
                    diaryLibraryRVAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 404) {
                    Log.e("noDiary!!!!!!!", "404~!!gg")
                    mBinding?.fragmentDiaryLibraryNoDiaryTv?.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<ResponseMyDiaryData>, t: Throwable) {
ut
            }

        })

        return mBinding?.root
    }
}