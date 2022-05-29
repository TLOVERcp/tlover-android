package com.cookandroid.teamproject1.myInfo.view.fragment

import android.app.Service
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentMyInfoLikeBinding
import com.cookandroid.teamproject1.myInfo.model.ResponseLikeData
import com.cookandroid.teamproject1.myInfo.model.ResponseRecentData
import com.cookandroid.teamproject1.myInfo.model.ResponseScrapData
import com.cookandroid.teamproject1.myInfo.view.adapter.MyInfoLikeRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoLikeFragment : Fragment() {
    private var mBinding : FragmentMyInfoLikeBinding?= null
    private var myInfoLikeRVAdapter = MyInfoLikeRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoLikeBinding.inflate(inflater, container, false)
        mBinding = binding

        mBinding?.fragmentLikeRv?.adapter = myInfoLikeRVAdapter

        /**
         * 좋아요한 다이어리 api 연동
         * 0526 윤성식
         */

        val call: Call<ResponseLikeData> = ServiceCreator.myInfoService.getLikeDiary(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
        )

        call.enqueue(object : Callback<ResponseLikeData>{
            override fun onResponse(
                call: Call<ResponseLikeData>,
                response: Response<ResponseLikeData>
            ) {
                if(response.code() == 200){
                    Log.e("응답 성공", "200!!!!")
                    val body = response.body()
                    if (body != null) {
                        Log.e("response", "notNull~!!")
                        val data = body.data.data
                        myInfoLikeRVAdapter.likeDiary = data
                    }
                    myInfoLikeRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseLikeData>, t: Throwable) {
                Log.e("response", "MyInfoNull~!!")

            }

        })

        return mBinding?.root
    }
}