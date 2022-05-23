package com.cookandroid.teamproject1.search.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentHomeBinding
import com.cookandroid.teamproject1.databinding.FragmentSearchBinding
import com.cookandroid.teamproject1.search.adapter.SearchRVAdapter
import com.cookandroid.teamproject1.search.model.ResponseSearchDiary
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 검색화면
 */
class SearchFragment : Fragment(){

    private var mBinding : FragmentSearchBinding?= null
    private var searchRVAdapter = SearchRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        mBinding = binding
        /**
         * 검색한 다이어리 조회 api 연결
         * 0524
         * 작성자 : 윤성식
         */
        mBinding?.fragmentSearchRv?.adapter = searchRVAdapter

        mBinding?.fragmetSearchIcIv?.setOnClickListener {
            if(mBinding?.fragmentSearchEdittext?.text.toString().length >= 2) {

                val call: Call<ResponseSearchDiary> = ServiceCreator.searchService.getSearchDiary(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    mBinding?.fragmentSearchEdittext?.text.toString()
                )

                call.enqueue(object : Callback<ResponseSearchDiary> {
                    override fun onResponse(
                        call: Call<ResponseSearchDiary>,
                        response: Response<ResponseSearchDiary>
                    ) {
                        if (response.code() == 200) {
                            Log.e("응답 성공", "200!!!!")
                            val body = response.body()
                            if (body != null) {
                                Log.e("response", "notNull~!!")
                                val data = body.data.data
                                searchRVAdapter.searchDiary = data
                            }
                            searchRVAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSearchDiary>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
            else{
                Toast.makeText(requireActivity(), "검색어 두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}