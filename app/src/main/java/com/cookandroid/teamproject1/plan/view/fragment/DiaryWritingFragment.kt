package com.cookandroid.teamproject1.plan.view.fragment

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
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import java.lang.Exception
import java.util.jar.Manifest

class DiaryWritingFragment : Fragment() {
    private var mBinding : FragmentDiaryWritingBinding?=null
    val requestGetImage = 105
    var picCount = 0
    var selectPicNum = 1

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

        mBinding?.signUpingBackImg?.setOnClickListener {

        }
        mBinding?.fragmentDiaryWriteCalendarImg?.setOnClickListener {


            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireActivity(),
                R.style.DatePickerTheme,DatePickerDialog.OnDateSetListener { view, myear, month, mdayOfMonth ->
                val mmonth2 = month+1
                mBinding?.fragmentDiaryWriteDateEt?.setText(""+ mdayOfMonth +"/"+ mmonth2+ "/"+ myear)
                mBinding?.fragmentDiaryWriteDateEt?.setTextColor(Color.BLACK)
            }, year, month, day)

            datePickerDialog.show()
        }

        mBinding?.fragmentDiaryWritePicturePlus?.setOnClickListener {
            getPicture()
            selectPicNum=1
        }

        mBinding?.fragmentDiaryWritePicturePlus2?.setOnClickListener {
            getPicture()
            selectPicNum=2
        }

        mBinding?.fragmentDiaryWritePicturePlus3?.setOnClickListener {
            getPicture()
            selectPicNum=3
        }

        mBinding?.fragmentDiaryWritePicturePlus4?.setOnClickListener {
            getPicture()
            selectPicNum=4
        }

        mBinding?.fragmentDiaryWritePicturePlus5?.setOnClickListener {
            getPicture()
            selectPicNum=5
        }

        mBinding?.fragmentDiaryWritePicturePlus6?.setOnClickListener {
            getPicture()
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

    private fun getPicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,requestGetImage)

//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        intent.type = "image/*"
//        startActivityForResult(intent, OPEN_GALLERY)

    }

    }

