package com.cookandroid.teamproject1.plan.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryPlanBinding
import com.cookandroid.teamproject1.plan.model.ResponseDiaryPlanData
import com.cookandroid.teamproject1.plan.model.DiaryPlanDataModel
import com.cookandroid.teamproject1.plan.view.adapter.DiaryPlanRVAdapter
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * diary plan view
 * 다이어리 계획 모음
 */
class DiaryPlanFragment : Fragment() {

    private var mBinding : FragmentDiaryPlanBinding?=null
    private var dataList = ArrayList<DiaryPlanDataModel>()
    private var diaryPlanRVAdapter = DiaryPlanRVAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryPlanBinding.inflate(inflater, container, false)
        mBinding = binding
//        dataList.apply{
//            add(DiaryPlanDataModel("title1", "1","29","2022.02.06","Pyeongtaek","10209","17855 bijeon","비전동 투어 우와우와"))
//            add(DiaryPlanDataModel("title2", "1","26","2022.02.09","Seoul","30206","17811 Namgajwa","서대문구 짱짱"))
//            add(DiaryPlanDataModel("title3", "1","58","2022.05.08","Jeju","60508","17822 Aewol","제주 애월 꺄악"))
//            add(DiaryPlanDataModel("title1", "1","29","2022.02.06","Pyeongtaek","10209","17855 bijeon","비전동 투어 우와우와"))
//            add(DiaryPlanDataModel("title2", "1","26","2022.02.09","Seoul","30206","17811 Namgajwa","서대문구 짱짱"))
//            add(DiaryPlanDataModel("title3", "1","58","2022.05.08","Jeju","60508","17822 Aewol","제주 애월 꺄악"))
//
//        }
//
//        val diaryPlanRVAdapter=DiaryPlanRVAdapter(dataList)



        mBinding?.fragmentDiaryPlanRandomRv?.adapter = diaryPlanRVAdapter

        val call: Call<ResponseDiaryPlanData> = ServiceCreator.planService.getDiaryPlan(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt()

        )


        call.enqueue(object: Callback<ResponseDiaryPlanData> {
            override fun onResponse(
                call: Call<ResponseDiaryPlanData>,
                responsePlan: Response<ResponseDiaryPlanData>
            ) {


                if (responsePlan.code() == 200){
                    Log.e("response", "200success")
                    val body = responsePlan.body()
                    if (body != null) {
                        Log.e("response", "notnull")
                        val data = body.data
                        println(data)
                        diaryPlanRVAdapter.diaryPlanList = data
                    }
                    diaryPlanRVAdapter.notifyDataSetChanged()
                }

                //                                    dataList.apply {
                //                                        add(
                //                                            DiaryPlanDataModel(
                //                                                body.result[0].title,
                //                                                "1",
                //                                                body.result[0].writeDate,
                //                                                body.result[0].startDate,
                //                                                body.result[0].publicStatus,
                //                                                body.result[0].view,
                //                                                body.result[0].context,
                //
                //                                                )
                //                                        )
                //                                    }
                //
                //                                }
                //                                val diaryPlanRVAdapter=DiaryPlanRVAdapter(dataList)
                //                                binding.fragmentDiaryPlanRandomRv.layoutManager = LinearLayoutManager(context)
                //                                diaryPlanRVAdapter.notifyDataSetChanged()
                //                            }
                //                        }
                //                    }
                //                }
            }

            override fun onFailure(call: Call<ResponseDiaryPlanData>, t: Throwable) {
                Log.e("response", "fail")

            }

        })

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}