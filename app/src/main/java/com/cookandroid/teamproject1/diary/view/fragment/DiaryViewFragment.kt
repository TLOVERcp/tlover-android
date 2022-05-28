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
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentDiaryViewBinding
import com.cookandroid.teamproject1.diary.model.*
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.model.PlanAcceptDataModel
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.plan.view.adapter.PlanAcceptRVAdapter
import com.cookandroid.teamproject1.plan.view.fragment.PlanAuthListFragmentDirections
import com.cookandroid.teamproject1.plan.view.fragment.SelectFragment
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
    private lateinit var planAcceptRVAdapter : PlanAcceptRVAdapter
    private var dataList = mutableListOf<PlanAcceptDataModel>()
    var heartShape = false
    var scrapShape = false
    private var planId : String = ""


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
        val startNum = args.start
        Log.d(SignUpViewModel.TAG, "onViewCreated: $diaryId")

        // x버튼 - 번호 받아와서 해보자 or fragmentListener finish X
        mBinding?.signUpingBackImg?.setOnClickListener(){
            if (startNum ==1){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToSearchFragment())

            }else if (startNum ==2){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToHomeFragment())

            }else if (startNum ==3){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToMyInfoFragment())

            }else if (startNum ==4){
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
                        planId.toInt()
                    ).enqueue(object: Callback<ResponsePlanViewData> {
                        override fun onResponse(
                            call: Call<ResponsePlanViewData>,
                            response: Response<ResponsePlanViewData>
                        ) {
                            if(response.code() == 200){
                                Log.e("reponse", "200!!~~~")
                                for (i in 0 until response.body()?.data?.users?.size!!){
                                    dataList.add(PlanAcceptDataModel(response.body()?.data?.users!![i]))
                                }
                                planAcceptRVAdapter.setDataList(dataList)

                            }

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
                    mBinding?.diaryViewList = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseDiaryViewData>, t: Throwable) {

            }

        })

        val call2: Call<ResponseDiaryScrapNumData> = ServiceCreator.diaryService.getScrapNum(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            diaryId
        )

        call2.enqueue(object : Callback<ResponseDiaryScrapNumData> {
            override fun onResponse(
                call: Call<ResponseDiaryScrapNumData>,
                response: Response<ResponseDiaryScrapNumData>
            ) {
                if(response.code()==200) {
                    Log.e("response!!","200!!!!")
                    mBinding?.diaryScrap = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseDiaryScrapNumData>, t: Throwable) {

            }

        })

        val call3: Call<ResponseDiaryLikeNumData> = ServiceCreator.diaryService.getLikeNum(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            diaryId
        )

        call3.enqueue(object: Callback<ResponseDiaryLikeNumData> {
            override fun onResponse(
                call: Call<ResponseDiaryLikeNumData>,
                response: Response<ResponseDiaryLikeNumData>
            ) {
                if(response.code()==200) {
                    Log.e("response!!!", "200!!!!!")
                    mBinding?.diaryLike = response.body()?.data

                }

            }

            override fun onFailure(call: Call<ResponseDiaryLikeNumData>, t: Throwable) {

            }

        })

        //해당 유저의 해당 다이어리 스크랩 여부 조회
        val call6: Call<ResponseDiaryScrapWhetherData> = ServiceCreator.diaryService.getScrapWhether(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            requestScrapWhetherData
        )

        call6.enqueue(object : Callback<ResponseDiaryScrapWhetherData>{
            override fun onResponse(
                call: Call<ResponseDiaryScrapWhetherData>,
                response: Response<ResponseDiaryScrapWhetherData>
            ) {
                Log.e("response~~!!", "200?!?!")
                if (response.body()?.data?.scraped==true) {
                    scrapShape=true
                }
                else if (response.body()?.data?.scraped==false) {
                    scrapShape=false
                }

                scrapShape()
            }

            override fun onFailure(call: Call<ResponseDiaryScrapWhetherData>, t: Throwable) {

            }

        })

        val call7: Call<ResponseDiaryLikeWhetherData> = ServiceCreator.diaryService.getLikeWhether(
            TloverApplication.prefs.getString("jwt","null"),
            TloverApplication.prefs.getString("refreshToken","null").toInt(),
            requestLikeWhetherData
        )

        call7.enqueue(object : Callback<ResponseDiaryLikeWhetherData>{
            override fun onResponse(
                call: Call<ResponseDiaryLikeWhetherData>,
                response: Response<ResponseDiaryLikeWhetherData>
            ) {
                if (response.body()?.data?.liked==true) {
                    heartShape=true
                }
                else if (response.body()?.data?.liked==false) {
                    heartShape=false
                }

                heartShape()
            }

            override fun onFailure(call: Call<ResponseDiaryLikeWhetherData>, t: Throwable) {

            }

        })


       mBinding?.itemSearchViewHeartIcon?.setOnClickListener {
            //좋아요 API 연동
            val call4: Call<ResponseLikeData> = ServiceCreator.diaryService.getLike(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                diaryId
            )

            call4.enqueue(object: Callback<ResponseLikeData> {
                override fun onResponse(
                    call: Call<ResponseLikeData>,
                    response: Response<ResponseLikeData>
                ) {
                    Log.e("response!!!!", "200?!")
                    if (response.body()?.data?.liked==true) {
                        heartShape=true
                    }
                    else if (response.body()?.data?.liked==false) {
                        heartShape=false
                    }

                }

                override fun onFailure(call: Call<ResponseLikeData>, t: Throwable) {

                }

            } )

            val call3: Call<ResponseDiaryLikeNumData> = ServiceCreator.diaryService.getLikeNum(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                diaryId
            )

            call3.enqueue(object: Callback<ResponseDiaryLikeNumData> {
                override fun onResponse(
                    call: Call<ResponseDiaryLikeNumData>,
                    response: Response<ResponseDiaryLikeNumData>
                ) {
                    if(response.code()==200) {
                        Log.e("response!!!", "200!!!!!")
                        mBinding?.diaryLike = response.body()?.data

                    }

                }

                override fun onFailure(call: Call<ResponseDiaryLikeNumData>, t: Throwable) {

                }

            })

            heartShape()
        }

        mBinding?.itemSearchViewScrapIcon?.setOnClickListener {
            val call5 : Call<ResponseScrapData> = ServiceCreator.diaryService.getScrap(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                requestScrapData
            )

            call5.enqueue(object : Callback<ResponseScrapData>{
                override fun onResponse(
                    call: Call<ResponseScrapData>,
                    response: Response<ResponseScrapData>
                ) {
                    if(response.code()==200) {
                        Log.e("response~", "200?!?!")
                    }
                }

                override fun onFailure(call: Call<ResponseScrapData>, t: Throwable) {

                }

            })
            val call2: Call<ResponseDiaryScrapNumData> = ServiceCreator.diaryService.getScrapNum(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                diaryId
            )

            call2.enqueue(object : Callback<ResponseDiaryScrapNumData> {
                override fun onResponse(
                    call: Call<ResponseDiaryScrapNumData>,
                    response: Response<ResponseDiaryScrapNumData>
                ) {
                    if(response.code()==200) {
                        Log.e("response!!","200!!!!")
                        mBinding?.diaryScrap = response.body()?.data
                    }
                }

                override fun onFailure(call: Call<ResponseDiaryScrapNumData>, t: Throwable) {

                }

            })
            //스크랩 여부 조회
            val call6: Call<ResponseDiaryScrapWhetherData> = ServiceCreator.diaryService.getScrapWhether(
                TloverApplication.prefs.getString("jwt","null"),
                TloverApplication.prefs.getString("refreshToken","null").toInt(),
                requestScrapWhetherData
            )

            call6.enqueue(object : Callback<ResponseDiaryScrapWhetherData>{
                override fun onResponse(
                    call: Call<ResponseDiaryScrapWhetherData>,
                    response: Response<ResponseDiaryScrapWhetherData>
                ) {
                    Log.e("response~~!!", "200?!?!")
                    if (response.body()?.data?.scraped==true) {
                        scrapShape=true
                    }
                    else if (response.body()?.data?.scraped==false) {
                        scrapShape=false
                    }

                    scrapShape()
                }

                override fun onFailure(call: Call<ResponseDiaryScrapWhetherData>, t: Throwable) {

                }

            })


        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun heartShape() {
        if(heartShape==true) {
            mBinding?.itemSearchViewHeartIcon?.setImageResource(R.drawable.ic_colored_heart)
        }
        else if(heartShape==false) {
            mBinding?.itemSearchViewHeartIcon?.setImageResource(R.drawable.diary_search_heart)
        }
    }

    private fun scrapShape() {
        if(scrapShape==true) {
            mBinding?.itemSearchViewScrapIcon?.setImageResource(R.drawable.ic_colored_scrap)
        }
        else if(scrapShape==false) {
            mBinding?.itemSearchViewScrapIcon?.setImageResource(R.drawable.diary_search_scrap)
        }
    }

}