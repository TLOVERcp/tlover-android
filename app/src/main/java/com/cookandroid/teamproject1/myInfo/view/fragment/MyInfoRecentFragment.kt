package com.cookandroid.teamproject1.myInfo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoRecentBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseRecentData
import com.cookandroid.teamproject1.myInfo.view.adapter.MyInfoRecentRVAdapter
import com.cookandroid.teamproject1.search.model.ResponseSearchDiary
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoRecentFragment : Fragment() {
    private var mBinding : FragmentMyInfoRecentBinding?= null
    private var myInfoRecentRVAdapter = MyInfoRecentRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoRecentBinding.inflate(inflater, container, false)
        mBinding = binding

        mBinding?.fragmentRecentRv?.adapter = myInfoRecentRVAdapter

        /**
         * 최근 본 다이어리 api 연동
         * 0526 윤성식
         */
        val call: Call<ResponseRecentData> = ServiceCreator.myInfoService.getHistoryDiary(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
        )

        call.enqueue(object : Callback<ResponseRecentData> {
            override fun onResponse(
                call: Call<ResponseRecentData>,
                response: Response<ResponseRecentData>
            ) {
                if (response.code() == 200) {
                    Log.e("응답 성공", "200!!!!")
                    val body = response.body()
                    if (body != null) {
                        Log.e("response", "notNull~!!")
                        val data = body.data
                        myInfoRecentRVAdapter.recentDiary = data
                    }
                    myInfoRecentRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseRecentData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return mBinding?.root
    }

}