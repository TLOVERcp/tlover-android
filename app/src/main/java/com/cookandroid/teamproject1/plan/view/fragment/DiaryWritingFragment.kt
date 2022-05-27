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
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import java.io.IOException
import java.lang.Exception
import java.util.jar.Manifest

class DiaryWritingFragment : Fragment() {
    private var mBinding : FragmentDiaryWritingBinding?=null
    val requestGetImage = 105
    var picCount = 0
    var selectPicNum = 1
    private var OPEN_GALLERY = 1

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
                                mBinding?.fragmentDiaryWritePictureContainer?.setImageBitmap(bitmap)
                                mBinding?.fragmentDiaryWritePicturePlus?.visibility=View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
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

