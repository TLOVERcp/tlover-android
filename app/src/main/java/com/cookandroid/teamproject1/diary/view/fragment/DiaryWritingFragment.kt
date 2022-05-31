package com.cookandroid.teamproject1.diary.view.fragment
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentDiaryWritingBinding
import com.cookandroid.teamproject1.diary.model.ResponseDiaryWriteData
import com.cookandroid.teamproject1.plan.model.ResponsePlanViewData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class DiaryWritingFragment : Fragment() {
    private var mBinding: FragmentDiaryWritingBinding? = null
    val requestGetImage = 105
    var picCount = 0
    var selectPicNum = 1
    private var OPEN_GALLERY = 1
    val PERMISSIONS_REQUEST_CODE = 101

    // null 방지 사진은?
    var isTitle: Boolean = false
    var isContext: Boolean = false
    var REQUIRED_PERMISSIONS = arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE)


    private var list: ArrayList<String> = arrayListOf()
    private var listed: List<String> = arrayListOf()
    private var selectdata: ArrayList<String> = arrayListOf()
    private var text: String = "text"

    private var filePhoto : ArrayList<File> = arrayListOf()
    private var regionList: ArrayList<MultipartBody.Part> = arrayListOf()
    private var themaList: ArrayList<MultipartBody.Part> = arrayListOf()
    private var photoList: ArrayList<MultipartBody.Part> = arrayListOf()

    // null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryWritingBinding.inflate(inflater, container, false)
        mBinding = binding
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // api 연동
        // 기존값 셋팅해주기
        val args: DiaryWritingFragmentArgs by navArgs()
        val planId = args.planId

        val call: Call<ResponsePlanViewData> = ServiceCreator.planService.getDiaryPlanView(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            planId.toInt()
        )

        call.enqueue(object : Callback<ResponsePlanViewData> {
            override fun onResponse(
                call: Call<ResponsePlanViewData>,
                response: Response<ResponsePlanViewData>
            ) {
                if (response.code() == 200) {
                    Log.e("reponse", "200!!~~~")
                    mBinding?.fragmentDiaryWriteDateEt?.setText(
                        response.body()?.data?.planStartDate?.substring(
                            0,
                            10
                        )
                    )
                    mBinding?.fragmentDiaryWriteEndDateEt?.setText(
                        response.body()?.data?.planEndDate?.substring(
                            0,
                            10
                        )
                    )
                    mBinding?.fragmentDiaryWritePayEt?.setText(response.body()?.data?.expense.toString())
                    mBinding?.fragmentDiaryWriteLocationEt?.setText(response.body()?.data?.regionDetail)
                    mBinding?.fragmentDiaryWriteDateEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWriteEndDateEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWritePayEt?.setTextColor(Color.parseColor("#2E2E33"))
                    mBinding?.fragmentDiaryWriteLocationEt?.setTextColor(Color.parseColor("#2E2E33"))
                    listed = mBinding?.fragmentDiaryWriteLocationEt?.text!!.split(", ")
                    for (i in listed.indices) {
                        list.add(listed[i])
                    }
                }
            }

            override fun onFailure(call: Call<ResponsePlanViewData>, t: Throwable) {

            }
        })

        // 테마 선택 기능
        val arrB: ArrayList<Button> = arrayListOf()
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

        for (i in 0 until arrB.size) {
            arrB[i].setOnClickListener() {

                for (j in 0 until selectdata.size) {
                    if (selectdata[j] == arrB[i].text.toString()) {
                        selectdata.removeAt(j)
                        arrB[i].setBackgroundResource(R.drawable.plan_select_region_background)
                        arrB[i].setTextColor(Color.parseColor("#2E2E33"))
                        return@setOnClickListener
                    }
                }
                // 데이터 최대 선택 3개
                if (selectdata.size == 3) {
                    return@setOnClickListener
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
            if (mBinding?.fragmentDiaryWriteTitleEdittext?.text == null || mBinding?.fragmentDiaryContentTv?.text == null ||
                arrB[0] == null
            ) {
                Toast.makeText(requireActivity(), "제목, 내용, 테마를 모두 작성해주세요.", Toast.LENGTH_SHORT)
                    .show()
            } else {

                Log.e("", list.toString())
                Log.e("", selectdata.toString())

                fun String?.toPlainRequestBody() =
                    requireNotNull(this).toRequestBody("text/plain".toMediaTypeOrNull())
//            var file = File(uri.getPath())
//            val fileBody: RequestBody

//            fun File?.toImgRequestBody() = this?.asRequestBody("multipart/form-data; boundary=<calculated when request is sent>".toMediaTypeOrNull())

//            arrayListOf(MultipartBody.Part.createFormData("diaryImages", File(photoUri?.path).name,
//                File(photoUri?.path).toImgRequestBody()!!
//            )),
                // uri 넣는 부분에, 함수를 호출

//        val file = File(mediaPath)
//        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())


                //            fun File?.toImgRequestBody() = this?.asRequestBody("image/jpeg".toMediaTypeOrNull())
                fun Uri?.toPath() {

                }

                fun File?.toImgRequestBody() = this?.asRequestBody("image/*".toMediaTypeOrNull())
//        fun File?.toImgRequestBody() = this?.asRequestBody("image/*".toMediaType())

                for (i in 0 until list.size) {
                    var part = MultipartBody.Part.createFormData("regionName", list[i])
                    regionList.add(part)
                }

                for (i in 0 until selectdata.size) {
                    var part = MultipartBody.Part.createFormData("themaName", selectdata[i])
                    themaList.add(part)
                }

                for (i in 0 until filePhoto.size) {
                    var part = MultipartBody.Part.createFormData(
                        "diaryImages",
                        filePhoto[i].name,
                        filePhoto[i].toImgRequestBody()!!
                    )
                    photoList.add(part)
                }

                val call: Call<ResponseDiaryWriteData> = ServiceCreator.diaryService.createDiary(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    MultipartBody.Part.createFormData(
                        "diaryTitle",
                        mBinding?.fragmentDiaryWriteTitleEdittext?.text.toString()
                    ),
                    MultipartBody.Part.createFormData(
                        "diaryContext",
                        mBinding?.fragmentDiaryContentTv?.text.toString()
                    ),
                    photoList.toList(),
                    MultipartBody.Part.createFormData(
                        "diaryStartDate",
                        mBinding?.fragmentDiaryWriteDateEt?.text.toString() + " 00:00:00"
                    ),
                    MultipartBody.Part.createFormData(
                        "diaryEndDate",
                        mBinding?.fragmentDiaryWriteEndDateEt?.text.toString() + " 23:59:59"
                    ),
                    regionList.toList(),
                    themaList.toList(),
                    MultipartBody.Part.createFormData(
                        "totalCost",
                        mBinding?.fragmentDiaryWritePayEt?.text.toString()
                    ),
                    MultipartBody.Part.createFormData("planId", planId.toString())
                )

                call.enqueue(object : Callback<ResponseDiaryWriteData> {
                    override fun onResponse(
                        call: Call<ResponseDiaryWriteData>,
                        response: Response<ResponseDiaryWriteData>
                    ) {
                        if (response.code() == 200) {
                            Log.e("diaryWrite_server_test", "200")
                            Toast.makeText(requireActivity(), "작성되었습니다.", Toast.LENGTH_SHORT).show()
                            val action =
                                DiaryWritingFragmentDirections.actionDiaryWritingFragmentToDiaryFragment()
                            it.findNavController().navigate(action)
                        } else {
                            Log.e("diaryWrite_server_test", "codeFail")
                            Toast.makeText(
                                requireActivity(),
                                "제목, 내용, 테마 모두 작성해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseDiaryWriteData>, t: Throwable) {
                        Log.w("MyTag", "requestFailed", t);
                    }
                })

            }

        }
        // 뒤로 가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener() {
            val action =
                DiaryWritingFragmentDirections.actionDiaryWritingFragmentToPlanViewFragment(planId)
            it.findNavController().navigate(action)
        }


        // 갤러리 여는 것
        mBinding?.fragmentDiaryWritePicturePlus?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
//                Toast.makeText(requireActivity(), "죄송합니다. 서비스 준비중입니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                // override
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 1
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus2?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 2
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus3?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 3
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus4?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 4
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus5?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 5
            }
        })

        mBinding?.fragmentDiaryWritePicturePlus6?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityForResult(intent, OPEN_GALLERY)
                selectPicNum = 6
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @Throws(IOException::class)
    fun createFile(context: Context, fileName: String?, extension: String?): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        return File(storageDir, "$fileName.$extension")
    }

    private fun inputStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }

    //사진 관련
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == OPEN_GALLERY) {
            if (resultCode == RESULT_OK) {
                // 클릭한 사진 uri
                var currentImageUri = data?.data
                requireActivity().contentResolver.openInputStream(currentImageUri!!)?.use { inputStream ->
                    val file = createFile(requireContext(), "temp", "jpg")
                    inputStreamToFile(inputStream, file)
                    filePhoto.add(file)
                }

                try {
                    currentImageUri.let {
                        if (selectPicNum == 1) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus?.visibility = View.GONE
                            }
                        }

                        if (selectPicNum == 2) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer2?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus2?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer2?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus2?.visibility = View.GONE
                            }
                        }

                        if (selectPicNum == 3) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer3?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus3?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer3?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus3?.visibility = View.GONE
                            }
                        }

                        if (selectPicNum == 4) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer4?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus4?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer4?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus4?.visibility = View.GONE
                            }
                        }

                        if (selectPicNum == 5) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer5?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus5?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer5?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus5?.visibility = View.GONE
                            }
                        }

                        if (selectPicNum == 6) {
                            if (Build.VERSION.SDK_INT < 28) {
                                val bitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                mBinding?.fragmentDiaryWritePictureContainer6?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus6?.visibility = View.GONE
                            } else {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    currentImageUri
                                )
                                val bitmap = ImageDecoder.decodeBitmap(source)
                                mBinding?.fragmentDiaryWritePictureContainer6?.setImageBitmap(
                                    bitmap
                                )
                                mBinding?.fragmentDiaryWritePicturePlus6?.visibility = View.GONE
                            }
                        }
                        picCount++
                        checkPicCount()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(requireActivity(), "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

        //사진 개수 체크하여 빈 pic container를 나타나게한다.
        private fun checkPicCount() {
            if (picCount == 1) {
                mBinding?.fragmentDiaryWritePictureContainer2?.visibility = View.VISIBLE
                mBinding?.fragmentDiaryWritePicturePlus2?.visibility = View.VISIBLE
            }
            if (picCount == 2) {
                mBinding?.fragmentDiaryWritePictureContainer3?.visibility = View.VISIBLE
                mBinding?.fragmentDiaryWritePicturePlus3?.visibility = View.VISIBLE
            }
            if (picCount == 3) {
                mBinding?.fragmentDiaryWritePictureContainer4?.visibility = View.VISIBLE
                mBinding?.fragmentDiaryWritePicturePlus4?.visibility = View.VISIBLE
            }
            if (picCount == 4) {
                mBinding?.fragmentDiaryWritePictureContainer5?.visibility = View.VISIBLE
                mBinding?.fragmentDiaryWritePicturePlus5?.visibility = View.VISIBLE
            }
            if (picCount == 5) {
                mBinding?.fragmentDiaryWritePictureContainer6?.visibility = View.VISIBLE
                mBinding?.fragmentDiaryWritePicturePlus6?.visibility = View.VISIBLE
            } else {

            }
        }


    }


