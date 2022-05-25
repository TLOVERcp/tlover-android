package com.cookandroid.teamproject1.diary.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryWritingBinding
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import com.cookandroid.teamproject1.R
import java.util.*
import android.app.Activity
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.diary.model.ResponseDiaryWriteData
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.plan.model.ResponsePlanWriteData
import com.cookandroid.teamproject1.plan.view.fragment.PlanFriendInviteFragmentDirections
import com.cookandroid.teamproject1.plan.view.fragment.PlanViewFragmentArgs
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.jar.Manifest

class DiaryWritingFragment : Fragment() {
    private var mBinding : FragmentDiaryWritingBinding?=null
    val requestGetImage = 105
    var picCount = 0
    var selectPicNum = 1
    // null 방지 사진은?
    var isTitle : Boolean = false
    var isContext : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryWritingBinding.inflate(inflater,container,false)
        mBinding = binding

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // api 연동
        // 기존값 셋팅해주기
        val args : DiaryWritingFragmentArgs by navArgs()
        val planId = args.planId

        val call: Call<ResponsePlanViewData> = ServiceCreator.planService.getDiaryPlanView(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            planId.toInt()
        )

        call.enqueue(object: Callback<ResponsePlanViewData> {
            override fun onResponse(
                call: Call<ResponsePlanViewData>,
                response: Response<ResponsePlanViewData>
            ) {
                if(response.code() == 200){
                    Log.e("reponse", "200!!~~~")
                    mBinding?.fragmentDiaryWriteDateEt?.setText(response.body()?.data?.planStartDate?.substring(0,10))
                    mBinding?.fragmentDiaryWriteEndDateEt?.setText(response.body()?.data?.planEndDate?.substring(0,10))
                    mBinding?.fragmentDiaryWritePayEt?.setText(response.body()?.data?.expense.toString())
                    mBinding?.fragmentDiaryWriteLocationEt?.setText(response.body()?.data?.regionDetail)
                    mBinding?.fragmentDiaryWriteDateEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWriteEndDateEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWritePayEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWriteLocationEt?.setTextColor(Color.parseColor("#2E2E33"))
                }
            }


            override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {

            }
        })
        // 제목, 내용, planId, 사진 POST
        mBinding?.fragmentDiaryWriteSaveBt?.setOnClickListener() {
            if (mBinding?.fragmentDiaryContentTv?.text != null) {
                isContext = true
            }
            if (mBinding?.fragmentDiaryWriteTitleEdittext?.text != null) {
                isTitle = true
            }
            if (isContext && isTitle) {
                mBinding?.fragmentDiaryWriteSaveBt?.isEnabled = true
            }
//            val call: Call<ResponseDiaryWriteData> = ServiceCreator.diaryService.postDiaryWrite(
//                TloverApplication.prefs.getString("jwt", "null"),
//                TloverApplication.prefs.getString("refreshToken", "null").toInt(),
//                mBinding?.fragmentDiaryWriteTitleEdittext?.text.toString(),
//                mBinding?.fragmentDiaryContentTv?.text.toString(),
//                planId.toInt(),
//
//
//            )}

        }
        // 뒤로 가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener(){
            val action = DiaryWritingFragmentDirections.actionDiaryWritingFragmentToPlanViewFragment(planId)
            it.findNavController().navigate(action)

        }

        mBinding?.fragmentDiaryWritePicturePlus?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=1
        }

        mBinding?.fragmentDiaryWritePicturePlus2?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=2
        }

        mBinding?.fragmentDiaryWritePicturePlus3?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=3
        }

        mBinding?.fragmentDiaryWritePicturePlus4?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=4
        }

        mBinding?.fragmentDiaryWritePicturePlus5?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=5
        }

        mBinding?.fragmentDiaryWritePicturePlus6?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,requestGetImage)
            selectPicNum=6
        }

    }

    //사진 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                requestGetImage -> {
                    try{
                        var uri = data?.data
                        if(selectPicNum==1) {
                            mBinding?.fragmentDiaryWritePictureContainer?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus?.visibility=View.GONE
                        }
                        if(selectPicNum==2) {
                            mBinding?.fragmentDiaryWritePictureContainer2?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus2?.visibility=View.GONE
                        }
                        if(selectPicNum==3) {
                            mBinding?.fragmentDiaryWritePictureContainer3?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus3?.visibility=View.GONE
                        }
                        if(selectPicNum==4) {
                            mBinding?.fragmentDiaryWritePictureContainer4?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus4?.visibility=View.GONE
                        }
                        if(selectPicNum==5) {
                            mBinding?.fragmentDiaryWritePictureContainer5?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus5?.visibility=View.GONE
                        }
                        if(selectPicNum==6) {
                            mBinding?.fragmentDiaryWritePictureContainer6?.setImageURI(uri)
                            mBinding?.fragmentDiaryWritePicturePlus6?.visibility=View.GONE
                        }
                        else {

                        }
                        picCount++
                        checkPicCount()
                    }catch (e: Exception) {}
                }
            }
        }
    }

    //사진 개수 체크하여 빈 pic container를 나타나게한다.
    private fun checkPicCount() {
        if(picCount==1) {
            mBinding?.fragmentDiaryWritePictureContainer2?.visibility=View.VISIBLE
            mBinding?.fragmentDiaryWritePicturePlus2?.visibility=View.VISIBLE
        }
        if(picCount==2) {
            mBinding?.fragmentDiaryWritePictureContainer3?.visibility=View.VISIBLE
            mBinding?.fragmentDiaryWritePicturePlus3?.visibility=View.VISIBLE
        }
        if(picCount==3) {
            mBinding?.fragmentDiaryWritePictureContainer4?.visibility=View.VISIBLE
            mBinding?.fragmentDiaryWritePicturePlus4?.visibility=View.VISIBLE
        }
        if(picCount==4) {
            mBinding?.fragmentDiaryWritePictureContainer5?.visibility=View.VISIBLE
            mBinding?.fragmentDiaryWritePicturePlus5?.visibility=View.VISIBLE
        }
        if (picCount==5) {
            mBinding?.fragmentDiaryWritePictureContainer6?.visibility=View.VISIBLE
            mBinding?.fragmentDiaryWritePicturePlus6?.visibility=View.VISIBLE
        }
        else {

        }
    }




}
