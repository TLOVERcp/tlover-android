package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoScrapBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseLikeData
import com.cookandroid.teamproject1.myInfo.model.ResponseScrapData
import com.cookandroid.teamproject1.myInfo.view.adapter.MyInfoScrapRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoScrapFragment : Fragment() {
    private var mBinding : FragmentMyInfoScrapBinding?= null
    private var myInfoScrapRVAdapter = MyInfoScrapRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoScrapBinding.inflate(inflater, container, false)
        mBinding = binding

        mBinding?.fragmentScrapRv?.adapter = myInfoScrapRVAdapter

        /**
         * 스크랩 다이어리 api 연동
         * 0526 윤성식
         */

        val call : Call<ResponseScrapData> = ServiceCreator.myInfoService.getScrapDiary(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
        )

        call.enqueue(object : Callback<ResponseScrapData> {
            override fun onResponse(
                call: Call<ResponseScrapData>,
                response: Response<ResponseScrapData>
            ) {
                if(response.code() == 200){
                    Log.e("응답 성공", "200!!!!")
                    val body = response.body()
                    if (body != null) {
                        Log.e("response", "notNull~!!")
                        val data = body.data.data
                        myInfoScrapRVAdapter.scrapDiary = data
                    }
                    myInfoScrapRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseScrapData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        return mBinding?.root
    }

}