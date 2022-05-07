package com.cookandroid.teamproject1.home.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentHomeBinding
import com.cookandroid.teamproject1.home.model.HomeDataModel
import com.cookandroid.teamproject1.home.model.HomeHotRecommendDataModel
import com.cookandroid.teamproject1.home.model.ResponseAllDiaryData
import com.cookandroid.teamproject1.home.view.adapter.HomeHotRecommendRVAdapter
import com.cookandroid.teamproject1.home.view.adapter.HomeRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){

    private var mBinding : FragmentHomeBinding?= null
    private var dataList = ArrayList<HomeDataModel>()
    private var dataListSecond = ArrayList<HomeHotRecommendDataModel>()
    private var homeRVAdapter = HomeRVAdapter()

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding = binding

//        mbinding.Fragment.text = getString(R.string.home_user_hi).format(InfraApplication.prefs.getString("userNickName", "null"))
        // RVAdapter
        mBinding!!.fragmentHomeMainFirstTxt.text = "%s님, 안녕하세요!".format(TloverApplication.prefs.getString("userNickname", "null"))

//        dataList.apply{
//            add(HomeDataModel("title1", R.drawable.img1_item_home_random,"2022.04.28","Peter","Queens"))
//            add(HomeDataModel("title2", R.drawable.img2_item_home_random,"2022.06.03","Tony","NewYork"))
//            add(HomeDataModel("title1", R.drawable.img1_item_home_random,"2022.04.28","Peter","Queens"))
//            add(HomeDataModel("title2", R.drawable.img2_item_home_random,"2022.06.03","Tony","NewYork"))
//            add(HomeDataModel("title1", R.drawable.img1_item_home_random,"2022.04.28","Peter","Queens"))
//            add(HomeDataModel("title2", R.drawable.img2_item_home_random,"2022.06.03","Tony","NewYork"))
//
//        }

        dataListSecond.apply {
            add(HomeHotRecommendDataModel("제목1",R.drawable.img2_item_home_random,"2022.05.04","187","Brooklyn"))
            add(HomeHotRecommendDataModel("제목2",R.drawable.img1_item_home_random,"2022.09.01","91","London"))
            add(HomeHotRecommendDataModel("제목1",R.drawable.img2_item_home_random,"2022.05.04","187","Brooklyn"))
            add(HomeHotRecommendDataModel("제목2",R.drawable.img1_item_home_random,"2022.09.01","91","London"))
            add(HomeHotRecommendDataModel("제목1",R.drawable.img2_item_home_random,"2022.05.04","187","Brooklyn"))
            add(HomeHotRecommendDataModel("제목2",R.drawable.img1_item_home_random,"2022.09.01","91","London"))

        }
        /**
         * 다이어리 취향 목록 조회 api 연동
         * 작성자 : 윤성식
         * 0508 마무리
         */
        mBinding?.fragmentHomeTitleRandomRv?.adapter = homeRVAdapter

        val call: Call<ResponseAllDiaryData> = ServiceCreator.diaryService.getDiary(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt()
        )

        call.enqueue(object: Callback<ResponseAllDiaryData>{
            override fun onResponse(
                call: Call<ResponseAllDiaryData>,
                response: Response<ResponseAllDiaryData>
            ) {
                if(response.code() == 200){
                    Log.e("reponse", "200!!")
                    val body = response.body()
                    if(body != null){
                        Log.e("response", "notNull")
                        val data = body.data
//                        println(data)
                        homeRVAdapter.preferDiaryList = data
                    }
                    homeRVAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseAllDiaryData>, t: Throwable) {
                Log.e("response", "fail")
            }

        })

//        val homeRVAdapter = HomeRVAdapter(dataList)
        val homeHotRecommendRVAdapter = HomeHotRecommendRVAdapter(dataListSecond)
//        binding.fragmentHomeTitleRandomRv.adapter = homeRVAdapter
        binding.fragmentHomeTitleRandomRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeTitleSameRv.adapter = homeHotRecommendRVAdapter
        binding.fragmentHomeTitleSameRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        return mBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val call: Call<ResponseAllDiaryData> = ServiceCreator.diaryService.getDiary(
//            TloverApplication.prefs.getString("jwt", "null"),
//            TloverApplication.prefs.getString("refreshToken", "null").toInt()
//        )
//
//        call.enqueue(object: Callback<ResponseAllDiaryData> {
//            override fun onResponse(
//                call: Call<ResponseAllDiaryData>,
//                response: Response<ResponseAllDiaryData>
//            ) {
//                if(response.code() == 200){
//                    println("gg")
//                }
//            }
//            override fun onFailure(call: Call<ResponseAllDiaryData>, t: Throwable) {
//            }
//
//        })


        mBinding?.fragmentHomePlanBt?.setOnClickListener(){
            //nav_graph 에서 프래그먼트 이동할 것을 이어준 후 이렇게 적어주면 프래그먼트간 이동 끝
            it.findNavController().navigate(R.id.action_homeFragment_to_planWriteFragment)

        }
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}