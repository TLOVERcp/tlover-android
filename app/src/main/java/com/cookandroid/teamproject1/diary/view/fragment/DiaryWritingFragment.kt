package com.cookandroid.teamproject1.diary.view.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.teamproject1.databinding.FragmentDiaryWritingBinding
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.diary.model.ResponseDiaryWriteData
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.plan.model.ResponsePlanWriteData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.ArrayList

class DiaryWritingFragment : Fragment() {
    private var mBinding : FragmentDiaryWritingBinding?=null
    val requestGetImage = 105
    var picCount = 0
    var selectPicNum = 1
    private var OPEN_GALLERY = 1
    // null 방지 사진은?
    var isTitle : Boolean = false
    var isContext : Boolean = false

    private var selectPhotoUri = mutableListOf<Uri>()
    private var selectPhotoString = mutableListOf<String>()
    private var list : ArrayList<String> = arrayListOf()
    private var listed : List<String> = arrayListOf()
    private var selectdata : ArrayList<String> = arrayListOf()
    private var photoList : ArrayList<File> = arrayListOf()


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
                    listed = mBinding?.fragmentDiaryWriteLocationEt?.text!!.split(", ")
                    for (i in listed.indices){
                        list.add(listed[i])
                    }
                }
            }
            override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {

            }
        })

        // 테마 선택 기능
        val arrB : ArrayList<Button> = arrayListOf()
        arrB.add(mBinding?.fragmentDiaryThemeButton1!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton2!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton3!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton4!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton5!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton6!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton7!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton8!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton9!!)
        arrB.add(mBinding?.fragmentDiaryThemeButton10!!)

        for (i in 0 until arrB.size){
            arrB[i].setOnClickListener(){
                for (j in 0 until selectdata.size){
                    if (selectdata[j] == arrB[i].text.toString()){
                        selectdata.removeAt(j)
                        arrB[i].setBackgroundResource(R.drawable.plan_select_region_background)
                        arrB[i].setTextColor(Color.parseColor("#2E2E33"))
                        return@setOnClickListener
                    }
                }
                selectdata.add(arrB[i].text.toString())
                arrB[i].setBackgroundResource(R.drawable.backgroud_fragment_select_select)
                arrB[i].setTextColor(Color.parseColor("#ffffff"))

            }
        }

        // 제목, 내용, planId, 사진 POST
            if (mBinding?.fragmentDiaryContentTv?.text != null) {
                isContext = true
            }
            if (mBinding?.fragmentDiaryWriteTitleEdittext?.text != null) {
                isTitle = true
            }
            if (isContext && isTitle) {
                mBinding?.fragmentDiaryWriteSaveBt?.isEnabled = true
            }
        mBinding?.fragmentDiaryWriteSaveBt?.setOnClickListener() {
            Log.e("", list.toString())
            Log.e("", selectdata.toString())

            val call: Call<ResponseDiaryWriteData> = ServiceCreator.diaryService.postDiaryWrite(
                TloverApplication.prefs.getString("jwt", "null"),
                TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                mBinding?.fragmentDiaryContentTv?.text.toString(),
                mBinding?.fragmentDiaryWriteEndDateEt?.text.toString()+ " 23:59:59",
                photoList,
                mBinding?.fragmentDiaryWriteDateEt?.text.toString()+ " 00:00:00",
                mBinding?.fragmentDiaryWriteTitleEdittext?.text.toString(),
                planId.toInt(),
                list,
                selectdata,
                mBinding?.fragmentDiaryWritePayEt?.text.toString().toInt()
            )

            call.enqueue(object : Callback<ResponseDiaryWriteData> {
                override fun onResponse(
                    call: Call<ResponseDiaryWriteData>,
                    response: Response<ResponseDiaryWriteData>
                ) {
                    if (response.code() == 200) {
                        Log.e("diaryWrite_server_test", "200")
                        Toast.makeText(requireActivity(), "작성되었습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("diaryWrite_server_test", "codeFail")
                    }
                }

                override fun onFailure(call: Call<ResponseDiaryWriteData>, t: Throwable) {

                }
            })

        }
        // 뒤로 가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener(){
            val action = DiaryWritingFragmentDirections.actionDiaryWritingFragmentToPlanViewFragment(planId)
            it.findNavController().navigate(action)
        }

        // 사진 임의로 request 형식 변환
//        val file = File(" /storage/emulated/0/Download/filename.pdf")
//        val requestFile = RequestBody.create(MediaType.parse("application/pdf"), file)
//        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//


        mBinding?.fragmentDiaryWritePicturePlus?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=1
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus2?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=2
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus3?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=3
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus4?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=4
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus5?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=5
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus6?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent,OPEN_GALLERY)
                selectPicNum=6
            }
        })
    }

    //사진 관련
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == OPEN_GALLERY)
        {
            if(resultCode == RESULT_OK)
            {
                var currentImageUri = data?.data

                try{
                    currentImageUri?.let {
                        if (selectPicNum == 1) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                selectPhotoString.add(currentImageUri.toString())
                                selectPhotoUri.add(currentImageUri)
                                mBinding?.fragmentDiaryWritePictureContainer?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                selectPhotoString.add(currentImageUri.toString())
                                selectPhotoUri.add(currentImageUri)
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus?.visibility=View.GONE
                            }
                        }

                        if(selectPicNum==2) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer2?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus2?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                selectPhotoString.add(currentImageUri.toString())
                                selectPhotoUri.add(currentImageUri)
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer2?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus2?.visibility=View.GONE
                            }
                        }

                        if(selectPicNum==3) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer3?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus3?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                selectPhotoString.add(currentImageUri.toString())
                                selectPhotoUri.add(currentImageUri)
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer3?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus3?.visibility=View.GONE
                            }
                        }

                        if(selectPicNum==4) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer4?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus4?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer4?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus4?.visibility=View.GONE
                            }
                        }

                        if(selectPicNum==5) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer5?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus5?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer5?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus5?.visibility=View.GONE
                            }
                        }

                        if(selectPicNum==6) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer6?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus6?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer6?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus6?.visibility=View.GONE
                            }
                        }
                        else {

                        }
                        picCount++
                        checkPicCount()
                    }
                }catch(e: IOException)
                {
                    e.printStackTrace()
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(requireActivity(), "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
        mBinding?.fragmentDiaryThemeButton1?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton2?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton3?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton4?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton5?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton6?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton7?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton8?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton9?.setOnClickListener {

        }
        mBinding?.fragmentDiaryThemeButton10?.setOnClickListener {

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

