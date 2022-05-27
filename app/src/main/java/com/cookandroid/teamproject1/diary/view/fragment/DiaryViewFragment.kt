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

        // x버튼 - 번호 받아와서 해보자 or fragmentListener finish
        mBinding?.signUpingBackImg?.setOnClickListener(){
            if (startNum ==1){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToSearchFragment())

            }else if (startNum ==2){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToHomeFragment())

            }else if (startNum ==3){
                it.findNavController().navigate(DiaryViewFragmentDirections.actionDiaryViewFragmentToMyInfoFragment())

            }
//            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        // RV- planId도 받아와야되는데 넘어가나? diaryid에 맞는거 있나 diaryId -> planId 방법
//        planAcceptRVAdapter = PlanAcceptRVAdapter(requireContext())
//        mBinding?.fragmentDiaryViewFrRv?.layoutManager = GridLayoutManager(requireContext(), 4)
//        mBinding?.fragmentDiaryViewFrRv?.adapter = planAcceptRVAdapter
//
//        ServiceCreator.planService.getDiaryPlanView(
//            TloverApplication.prefs.getString("jwt", "null"),
//            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
//            diaryId
//        ).enqueue(object: Callback<ResponsePlanViewData> {
//            override fun onResponse(
//                call: Call<ResponsePlanViewData>,
//                response: Response<ResponsePlanViewData>
//            ) {
//                if(response.code() == 200){
//                    Log.e("reponse", "200!!~~~")
//                    for (i in 0 until response.body()?.data?.users?.size!!){
//                        dataList.add(PlanAcceptDataModel(response.body()?.data?.users!![i]))
//                    }
//                    planAcceptRVAdapter.setDataList(dataList)
//
//                }
//                planAcceptRVAdapter.notifyDataSetChanged()
//
//            }
//
//            override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {
//                Log.d(SignUpViewModel.TAG, "onFailure: $t")
//            }
//        })



        val requestScrapData = RequestScrapData(
            diaryId = args.diaryId
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

        mBinding?.itemSearchViewHeartIcon?.setOnClickListener {
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


        }




        super.onViewCreated(view, savedInstanceState)
    }


}