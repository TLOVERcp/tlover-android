package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.system.Os.remove
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.diary.model.*
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.model.PlanAcceptDataModel
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.plan.view.adapter.PlanAcceptRVAdapter
import com.cookandroid.teamproject1.plan.view.fragment.PlanAuthListFragmentDirections
import com.cookandroid.teamproject1.plan.view.fragment.SelectFragment
import com.cookandroid.teamproject1.util.BaseFragment
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * diary 상세보기 프래그먼트
 */

class DiaryViewFragment : BaseFragment<FragmentDiaryViewBinding>(R.layout.fragment_diary_view){
    private lateinit var planAcceptRVAdapter : PlanAcceptRVAdapter
    private var dataList = mutableListOf<PlanAcceptDataModel>()

    var heartShape = false
    var scrapShape = false

    private var planId : String = ""


    override fun FragmentDiaryViewBinding.onCreateView() {


    }

    override fun FragmentDiaryViewBinding.onViewCreated() {
        val args : DiaryViewFragmentArgs by navArgs()
        val diaryId = args.diaryId
        val startNum = args.start
        Log.d(SignUpViewModel.TAG, "onViewCreated: $diaryId")


        dataList.clear()
        planAcceptRVAdapter = PlanAcceptRVAdapter(requireContext())
        binding.fragmentDiaryViewFrRv.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.fragmentDiaryViewFrRv.adapter = planAcceptRVAdapter

        planAcceptRVAdapter = PlanAcceptRVAdapter(requireContext())
        binding.fragmentDiaryViewFrRv.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.fragmentDiaryViewFrRv.adapter = planAcceptRVAdapter


        // x버튼 - 번호 받아와서 해보자 or fragmentListener finish X
        binding.signUpingBackImg.setOnClickListener(){
            if (startNum ==1){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToSearchFragment())

            }else if (startNum ==2){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToHomeFragment())

            }
            // 탭 레이아웃
            else if (startNum ==31){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToMyInfoFragment())

            }else if (startNum ==32){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToMyInfoFragment())

            }else if (startNum ==33){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToMyInfoFragment())
            }
            else if (startNum ==4){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToDiaryFragment())

            }
//            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }


        // RV- planId도 받아와야되는데 넘어가나? diaryid에 맞는거 있나 diaryId -> planId 방법
        ServiceCreator.diaryService.getDiaryPlanId(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            diaryId
        ).enqueue(object: Callback<ResponseDiaryPlanId> {

            override fun onResponse(
                call: Call<ResponseDiaryPlanId>,
                response: Response<ResponseDiaryPlanId>
            ) {
                if(response.code() == 200){
                    planId = response.body()?.data?.planId.toString()
                    ServiceCreator.planService.getDiaryPlanView(
                        TloverApplication.prefs.getString("jwt", "null"),
                        TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                        response.body()?.data?.planId.toString().toInt()
                    ).enqueue(object: Callback<ResponsePlanViewData> {
                        override fun onResponse(
                            call: Call<ResponsePlanViewData>,
                            response: Response<ResponsePlanViewData>
                        ) {
                            if(response.code() == 200){
                                Log.e("reponse", "rvsucccccccc")
                                Log.e("", response.body()?.data?.users?.size?.toString()!!)
                                Log.e("", response.body()?.data?.users!![0])

                                for (i in 0 until response.body()?.data?.users?.size!!){
                                    dataList.add(PlanAcceptDataModel(response.body()?.data?.users!![i]))
                                }
                                planAcceptRVAdapter.setDataList(dataList)

                            }
                            planAcceptRVAdapter.notifyDataSetChanged()


                        }

                        override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {
                            Log.d(SignUpViewModel.TAG, "onFailure: $t")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<ResponseDiaryPlanId>, t: Throwable) {
            }
        })

//        planAcceptRVAdapter = PlanAcceptRVAdapter(requireContext())
//        mBinding?.fragmentDiaryViewFrRv?.layoutManager = GridLayoutManager(requireContext(), 4)
//        mBinding?.fragmentDiaryViewFrRv?.adapter = planAcceptRVAdapter
//


        val requestScrapData = RequestScrapData(
            diaryId = args.diaryId
        )
        val requestScrapWhetherData = RequestScrapWhetherData(
            diaryId=args.diaryId
        )
        val requestLikeWhetherData = RequestLikeWhetherData(
            diaryId=args.diaryId
        )



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
                    binding.diaryViewList = response.body()?.data
                    val img = binding.diaryViewList!!.myFileKeys[0]
//                    "https://d1mwr8154tagzz.cloudfront.net/$url"
                    binding.imageView.let{
                        Glide.with(requireActivity())
                            .load("https://d1mwr8154tagzz.cloudfront.net/$img?w=360&h=270&qq=50")
                            .error(R.drawable.tlover_nlogo)
                            .into(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDiaryViewData>, t: Throwable) {

            }

        })

        //스크랩 수를 받아온다.
        val callScrapCount: Call<ResponseDiaryScrapNumData> = ServiceCreator.diaryService.getScrapNum(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            diaryId
        )

        callScrapCount.enqueue(object : Callback<ResponseDiaryScrapNumData> {
            override fun onResponse(
                call: Call<ResponseDiaryScrapNumData>,
                response: Response<ResponseDiaryScrapNumData>
            ) {
                if(response.code()==200) {
                    Log.e("response!!","200 scrapcount")
                    binding.diaryScrap = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseDiaryScrapNumData>, t: Throwable) {

            }

        })

        //좋아요 개수 받아오기
        val callLikeCount: Call<ResponseDiaryLikeNumData> = ServiceCreator.diaryService.getLikeNum(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            diaryId
        )

        callLikeCount.enqueue(object: Callback<ResponseDiaryLikeNumData> {
            override fun onResponse(
                call: Call<ResponseDiaryLikeNumData>,
                response: Response<ResponseDiaryLikeNumData>
            ) {
                if(response.code()==200) {
                    Log.e("response!!!", "200 likeCount")
                    binding.diaryLike = response.body()?.data

                }

            }

            override fun onFailure(call: Call<ResponseDiaryLikeNumData>, t: Throwable) {

            }

        })

        //해당 유저의 해당 다이어리 스크랩 여부 조회 (만약 스크랩이 이미 되어있을 경우에는 스크랩 색을 칠해져 있게 만든다)
        val callDiaryScrapWhether: Call<ResponseDiaryScrapWhetherData> = ServiceCreator.diaryService.getScrapWhether(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            requestScrapWhetherData
        )

        callDiaryScrapWhether.enqueue(object : Callback<ResponseDiaryScrapWhetherData>{
            override fun onResponse(
                call: Call<ResponseDiaryScrapWhetherData>,
                response: Response<ResponseDiaryScrapWhetherData>
            ) {
                if(response.code()==200) {
                    Log.e("response", "200 scrap whether")
                    if (response.body()?.data?.scraped == true) {
                        scrapShape = true
                    } else if (response.body()?.data?.scraped == false) {
                        scrapShape = false
                    }
                }
                scrapShape()

                
            }

            override fun onFailure(call: Call<ResponseDiaryScrapWhetherData>, t: Throwable) {

            }

        })

        //해당 인원의 좋아요 여부를 체크
        val callDiaryLikeWhether: Call<ResponseDiaryLikeWhetherData> = ServiceCreator.diaryService.getLikeWhether(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            requestLikeWhetherData
        )

        callDiaryLikeWhether.enqueue(object : Callback<ResponseDiaryLikeWhetherData>{
            override fun onResponse(
                call: Call<ResponseDiaryLikeWhetherData>,
                response: Response<ResponseDiaryLikeWhetherData>
            ) {if(response.code()==200) {
                Log.e("response", "200 like whether")
                if (response.body()?.data?.liked == true) {
                    heartShape = true
                } else if (response.body()?.data?.liked== false) {
                    heartShape = false
                }

            }
                heartShape()
            }

            override fun onFailure(call: Call<ResponseDiaryLikeWhetherData>, t: Throwable) {

            }

        })


        binding.itemSearchViewHeartIcon.setOnClickListener {
            //좋아요 API 연동
            val callLike: Call<ResponseLikeData> = ServiceCreator.diaryService.getLike(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                diaryId
            )

            callLike.enqueue(object: Callback<ResponseLikeData> {
                override fun onResponse(
                    call: Call<ResponseLikeData>,
                    response: Response<ResponseLikeData>
                ) {
                    if (response.code() == 200) {
                        Log.e("response!!!!", "200 like")
                        if (response.body()?.data?.liked == true) {
                            heartShape = true
                        } else if (response.body()?.data?.liked == false) {
                            heartShape = false
                        }
                        heartShape()

                        //다시 좋아요 개수 체크
                        val callLikeCount: Call<ResponseDiaryLikeNumData> = ServiceCreator.diaryService.getLikeNum(
                            TloverApplication.prefs.getString("jwt","null"),
                            TloverApplication.prefs.getString("refreshToken","null").toInt(),
                            diaryId
                        )

                        callLikeCount.enqueue(object: Callback<ResponseDiaryLikeNumData> {
                            override fun onResponse(
                                call: Call<ResponseDiaryLikeNumData>,
                                response: Response<ResponseDiaryLikeNumData>
                            ) {
                                if(response.code()==200) {
                                    Log.e("response!!!", "200 likeCount")
                                    binding.diaryLike = response.body()?.data

                                }

                            }

                            override fun onFailure(call: Call<ResponseDiaryLikeNumData>, t: Throwable) {

                            }

                        })
                    }
                }
                override fun onFailure(call: Call<ResponseLikeData>, t: Throwable) {

                }

            } )




        }
        //스크랩 api 연동
        binding.itemSearchViewScrapIcon.setOnClickListener {
            val callScrap : Call<ResponseScrapData> = ServiceCreator.diaryService.getScrap(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                requestScrapData
            )

            callScrap.enqueue(object : Callback<ResponseScrapData>{
                override fun onResponse(
                    call: Call<ResponseScrapData>,
                    response: Response<ResponseScrapData>
                ) {
                    if(response.code()==201) {
                        Log.e("response~", "201 scrap")

                        //스크랩 수를 받아온다.
                        val callScrapCount: Call<ResponseDiaryScrapNumData> = ServiceCreator.diaryService.getScrapNum(
                            TloverApplication.prefs.getString("jwt","null"),
                            TloverApplication.prefs.getString("refreshToken","null").toInt(),
                            diaryId
                        )

                        callScrapCount.enqueue(object : Callback<ResponseDiaryScrapNumData> {
                            override fun onResponse(
                                call: Call<ResponseDiaryScrapNumData>,
                                response: Response<ResponseDiaryScrapNumData>
                            ) {
                                if(response.code()==200) {
                                    Log.e("response!!","200 scrapcount")
                                    binding.diaryScrap = response.body()?.data
                                }
                            }

                            override fun onFailure(call: Call<ResponseDiaryScrapNumData>, t: Throwable) {

                            }

                        })

                        //해당 유저의 해당 다이어리 스크랩 여부 조회 (만약 스크랩이 이미 되어있을 경우에는 스크랩 색을 칠해져 있게 만든다)
                        val callDiaryScrapWhether: Call<ResponseDiaryScrapWhetherData> = ServiceCreator.diaryService.getScrapWhether(
                            TloverApplication.prefs.getString("jwt","null"),
                            TloverApplication.prefs.getString("refreshToken","null").toInt(),
                            requestScrapWhetherData
                        )

                        callDiaryScrapWhether.enqueue(object : Callback<ResponseDiaryScrapWhetherData>{
                            override fun onResponse(
                                call: Call<ResponseDiaryScrapWhetherData>,
                                response: Response<ResponseDiaryScrapWhetherData>
                            ) {
                                if(response.code()==200) {
                                    Log.e("response", "200 scrap whether")
                                    if (response.body()?.data?.scraped == true) {
                                        scrapShape = true
                                    } else if (response.body()?.data?.scraped == false) {
                                        scrapShape = false
                                    }
                                }
                                scrapShape()


                            }

                            override fun onFailure(call: Call<ResponseDiaryScrapWhetherData>, t: Throwable) {

                            }

                        })


                    }

                    if(response.code()==204) {
                        Log.e("response~", "204 scrap")

                        //스크랩 수를 받아온다.
                        val callScrapCount: Call<ResponseDiaryScrapNumData> = ServiceCreator.diaryService.getScrapNum(
                            TloverApplication.prefs.getString("jwt","null"),
                            TloverApplication.prefs.getString("refreshToken","null").toInt(),
                            diaryId
                        )

                        callScrapCount.enqueue(object : Callback<ResponseDiaryScrapNumData> {
                            override fun onResponse(
                                call: Call<ResponseDiaryScrapNumData>,
                                response: Response<ResponseDiaryScrapNumData>
                            ) {
                                if(response.code()==200) {
                                    Log.e("response!!","200 scrapcount")
                                    binding.diaryScrap = response.body()?.data
                                }
                            }

                            override fun onFailure(call: Call<ResponseDiaryScrapNumData>, t: Throwable) {

                            }

                        })

                        //해당 유저의 해당 다이어리 스크랩 여부 조회 (만약 스크랩이 이미 되어있을 경우에는 스크랩 색을 칠해져 있게 만든다)
                        val callDiaryScrapWhether: Call<ResponseDiaryScrapWhetherData> = ServiceCreator.diaryService.getScrapWhether(
                            TloverApplication.prefs.getString("jwt","null"),
                            TloverApplication.prefs.getString("refreshToken","null").toInt(),
                            requestScrapWhetherData
                        )

                        callDiaryScrapWhether.enqueue(object : Callback<ResponseDiaryScrapWhetherData>{
                            override fun onResponse(
                                call: Call<ResponseDiaryScrapWhetherData>,
                                response: Response<ResponseDiaryScrapWhetherData>
                            ) {
                                if(response.code()==200) {
                                    Log.e("response", "200 scrap whether")
                                    if (response.body()?.data?.scraped == true) {
                                        scrapShape = true
                                    } else if (response.body()?.data?.scraped == false) {
                                        scrapShape = false
                                    }
                                }
                                scrapShape()


                            }

                            override fun onFailure(call: Call<ResponseDiaryScrapWhetherData>, t: Throwable) {

                            }

                        })
                    }

                }

                override fun onFailure(call: Call<ResponseScrapData>, t: Throwable) {

                }


            })




        }

    }
    private fun heartShape() {
        if(heartShape==true) {
            binding.itemSearchViewHeartIcon.setImageResource(R.drawable.ic_colored_heart)
        }
        else if(heartShape==false) {
            binding.itemSearchViewHeartIcon.setImageResource(R.drawable.diary_search_heart)
        }
    }

    private fun scrapShape() {
        if(scrapShape==true) {
            binding.itemSearchViewScrapIcon.setImageResource(R.drawable.ic_colored_scrap)
        }
        else if(scrapShape==false) {
            binding.itemSearchViewScrapIcon.setImageResource(R.drawable.diary_search_scrap)
        }
    }

}