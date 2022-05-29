package com.cookandroid.teamproject1.plan.view.fragment
import android.os.Bundle
import android.util.Log
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentPlanWriteBinding
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
import com.cookandroid.teamproject1.diary.view.fragment.DiaryViewFragmentArgs
import com.cookandroid.teamproject1.diary.view.fragment.DiaryViewFragmentDirections
import com.cookandroid.teamproject1.home.view.fragment.HomeFragmentDirections
import com.cookandroid.teamproject1.plan.model.RequestPlanWriteData
import com.cookandroid.teamproject1.plan.model.ResponsePlanWriteData
import com.cookandroid.teamproject1.plan.viewmodel.SelectViewModel
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * 계획작성 프래그먼트
 */
class PlanWriteFragment : Fragment() {
    private var mBinding: FragmentPlanWriteBinding? = null
    private var inputText = ""
    private lateinit var sharedViewModel: SelectViewModel
    private var list: ArrayList<String> = arrayListOf()
    private var listed: List<String> = arrayListOf()
    private var existList: List<String> = arrayListOf()

    // null 값 방지
    var isExpense: Boolean = false
    var isRegion: Boolean = false
    var issDate: Boolean = false
    var iseDate: Boolean = false
    var isTitle: Boolean = false
    var isContext: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanWriteBinding.inflate(inflater, container, false)
        mBinding = binding
//        sharedViewModel = ViewModelProvider(this).get(SelectViewModel::class.java)
//        mBinding?.fragmentPlanWriteLocationEt?.setText(sharedViewModel.getA().toString())

//        sharedViewModel.currentInputRegion.observe(this, Observer {
//            mBinding?.fragmentPlanWriteLocationEt.text = it.toString()
//        }

        // fragmentListener
//        setFragmentResultListener("requestSecondKey") { requestKey, bundle ->
//            val result = bundle.getString("passKey").toString()
//            Log.e("", result)
//           }
//
//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            Log.e("listenerLocation..", "장소셋팅")
//            val result = bundle.getString("senderKey").toString()
//            val new = result.substring(1,result.length-1)
//            listed = new.split("/")
//            for (i in listed.indices){
//                list.add(listed[i])
//            }
//            mBinding?.fragmentPlanWriteLocationEt?.setText(new)
//            isRegion=true
//            mBinding?.fragmentPlanWriteLocationEt?.setTextColor(Color.parseColor("#2E2E33"))
//        }

        // 값 셋팅
//        mBinding?.fragmentPlanWriteDateEt?.text.toString() == ""
//        mBinding?.fragmentPlanWriteEndDateEt?.text.toString() == ""
//        mBinding?.fragmentPlanWritePayEt?.text.toString() == ""
//        mBinding?.fragmentDiaryContentTv?.text.toString() == ""
//        mBinding?.fragmentPlanWritePayEt?.text.toString() == ""
//        setFragmentResultListener("requestSecondKey") {requestKey, bundle ->
//            Log.e("listener", "기존셋팅")
//            val existResult = bundle.getString("passKey").toString()
//            existList = existResult.split("/ ")
//            mBinding?.fragmentPlanWriteDateEt?.setText(existList[0])
//            mBinding?.fragmentPlanWriteEndDateEt?.setText(existList[1])
//            mBinding?.fragmentPlanWritePayEt?.setText(existList[2])
//            mBinding?.fragmentDiaryContentTv?.setText(existList[3])
//            mBinding?.fragmentPlanWriteTitleEdittext?.setText(existList[4])
//        }
        return mBinding?.root

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args: PlanWriteFragmentArgs by navArgs()
        val startNum = args.start
        val existData = args.exist
        val regionData = args.region

        if (existData == "") {
        } else if (existData != "") {
            existList = existData.split("/ ")
            mBinding?.fragmentPlanWriteDateEt?.setText(existList[0])
            mBinding?.fragmentPlanWriteDateEt?.setTextColor(Color.BLACK)
            mBinding?.fragmentPlanWriteEndDateEt?.setText(existList[1])
            mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)
            mBinding?.fragmentPlanWritePayEt?.setText(existList[2])
            mBinding?.fragmentDiaryContentTv?.setText(existList[3])
            mBinding?.fragmentPlanWriteTitleEdittext?.setText(existList[4])
        }
        if (regionData == "") {

        } else if (regionData != "") {
            val new = regionData.substring(1, regionData.length - 1)
            listed = new.split("/")
            for (i in listed.indices) {
                list.add(listed[i])
            }
            mBinding?.fragmentPlanWriteLocationEt?.setText(new)
            isRegion = true
            mBinding?.fragmentPlanWriteLocationEt?.setTextColor(android.graphics.Color.parseColor("#2E2E33"))

        }

        // 뒤로 가기 버튼
        mBinding?.signUpingBackImg?.setOnClickListener() {
            if (startNum == 1) {
                it.findNavController()
                    .navigate(PlanWriteFragmentDirections.actionPlanWriteFragmentToHomeFragment())

            } else if (startNum == 2) {
                it.findNavController()
                    .navigate(PlanWriteFragmentDirections.actionPlanWriteFragmentToDiaryFragment())

            }
        }


        // 지역 선택
        mBinding?.fragmentPlanWriteLocationEt?.setOnClickListener() {
            val exist = mBinding?.fragmentPlanWriteDateEt?.text.toString() + "/ " +
                    mBinding?.fragmentPlanWriteEndDateEt?.text.toString() + "/ " +
                    mBinding?.fragmentPlanWritePayEt?.text.toString() + "/ " +
                    mBinding?.fragmentDiaryContentTv?.text.toString() + "/ " +
                    mBinding?.fragmentPlanWriteTitleEdittext?.text.toString()
            //fragment listener
//                val bundle3 = bundleOf("existKey" to exist)
//                Log.e("bundle3", exist)
//                mBinding?.fragmentPlanWriteLocationEt?.isEnabled = false
//                setFragmentResult("requestKeyT", bundle3)
////                it.findNavController().navigate(R.id.action_planWriteFragment_to_selectFragment)
//                activity?.supportFragmentManager?.beginTransaction()?.replace(
//                    R.id.container, SelectFragment())?.commit()

            // nav로
            val action = PlanWriteFragmentDirections.actionPlanWriteFragmentToSelectFragment(
                exist = exist,
                startNum = startNum,
                region = ""
            )
            it.findNavController().navigate(action)

        }

        // 경비 작성 정수만 - input type
//            mBinding?.fragmentPlanWritePayEt?.addTextChangedListener(object:TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                @RequiresApi(Build.VERSION_CODES.N)
//                override fun afterTextChanged(p0: Editable?) {
////                    val dec = DecimalFormat("#,###")
////                    val format = dec.format(Integer.parseInt(mBinding?.fragmentPlanWritePayEt?.text.toString()))
////                    mBinding?.fragmentPlanWritePayEt?.setText(format)
//                }
//
//            })

        mBinding?.fragmentPlanWriteDateEt?.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                R.style.DatePickerTheme,
                DatePickerDialog.OnDateSetListener { view, myear, month, mdayOfMonth ->
                    val mmonth2 = month + 1
                    var m = ""
                    m = if (mmonth2 < 10) {
                        "0$mmonth2"
                    } else {
                        mmonth2.toString()
                    }
                    var d = ""
                    d = if (mdayOfMonth < 10) {
                        "0$mdayOfMonth"
                    } else {
                        mdayOfMonth.toString()
                    }
                    mBinding?.fragmentPlanWriteDateEt?.setText("$myear-$m-$d")
                    issDate = true
                    mBinding?.fragmentPlanWriteDateEt?.setTextColor(Color.BLACK)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        mBinding?.fragmentPlanWriteEndDateEt?.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                R.style.DatePickerTheme,
                DatePickerDialog.OnDateSetListener { view, myear, month, mdayOfMonth ->
                    val mmonth2 = month + 1
                    var m = ""
                    m = if (mmonth2 < 10) {
                        "0$mmonth2"
                    } else {
                        mmonth2.toString()
                    }
                    var d = ""
                    d = if (mdayOfMonth < 10) {
                        "0$mdayOfMonth"
                    } else {
                        mdayOfMonth.toString()
                    }

                    iseDate = true
//                    if (mBinding?.fragmentPlanWriteDateEt?.toString().equals("")){
//                        val list = mBinding?.fragmentPlanWriteDateEt?.text.toString().split("-")
//                        if (list[0].toInt()==myear.toInt()){
//                            if (list[1].toInt()==m.toInt()){
//                                if (list[2].toInt()<=d.toInt()){
//                                    mBinding?.fragmentPlanWriteEndDateEt?.setText("" + myear + "-" + m + "-" + d)
//                                    mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)
//                                }else{
//                                    Toast.makeText(requireActivity(), "선택하신 날짜가 시작 일자보다 빠릅니다.", Toast.LENGTH_SHORT).show()
//                                }
//                            }else if(list[1].toInt()<m.toInt()){
//                                mBinding?.fragmentPlanWriteEndDateEt?.setText("" + myear + "-" + m + "-" + d)
//                                mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)
//                            }else{
//                                Toast.makeText(requireActivity(), "선택하신 날짜가 시작 일자보다 빠릅니다.", Toast.LENGTH_SHORT).show()
//                            }
//                        }else if (list[0].toInt()<myear.toInt()){
//                            mBinding?.fragmentPlanWriteEndDateEt?.setText("" + myear + "-" + m + "-" + d)
//                            mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)
//                        }else{
//                            Toast.makeText(requireActivity(), "선택하신 날짜가 시작 일자보다 빠릅니다.", Toast.LENGTH_SHORT).show()
//                        }
//                    }
                    mBinding?.fragmentPlanWriteEndDateEt?.setText("" + myear + "-" + m + "-" + d)
                    mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)


                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }


//        mBinding?.signUpingBackImg?.setOnClickListener(){
//            it.findNavController().navigate(R.id.action_planWriteFragment_to_diaryFragment)
//        }

        // api 연동
        mBinding?.fragmentPlanWriteSaveBt?.setOnClickListener() {
            if (!mBinding?.fragmentDiaryContentTv?.text?.toString().equals("")) {
                isContext = true
            }
            if (!mBinding?.fragmentPlanWritePayEt?.text?.toString().equals("")) {
                isExpense = true
            }

            if (!mBinding?.fragmentPlanWriteDateEt?.text?.toString().equals("")) {
                issDate = true
            }
            if (!mBinding?.fragmentPlanWriteEndDateEt?.text?.toString().equals("")) {
                iseDate = true
            }
            if (!mBinding?.fragmentPlanWriteLocationEt?.text?.toString().equals("")) {
                isRegion = true
            }

//            Log.e("", mBinding?.fragmentPlanWriteTitleEdittext?.toString().toString())


            if (!issDate || !iseDate || !isRegion || !isContext || !isExpense) {
                Toast.makeText(requireActivity(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (issDate && iseDate && isRegion && isContext && isExpense) {
                val requestPlanWriteData = RequestPlanWriteData(
                    mBinding?.fragmentPlanWritePayEt?.text.toString().toLong(),
                    mBinding?.fragmentDiaryContentTv?.text.toString(),
                    mBinding?.fragmentPlanWriteEndDateEt?.text.toString() + " 23:59:59",
                    mBinding?.fragmentPlanWriteDateEt?.text.toString() + " 00:00:00",
                    list,
                    mBinding?.fragmentPlanWriteTitleEdittext?.text.toString()
                )


                val call: Call<ResponsePlanWriteData> = ServiceCreator.planService.postPlanWrite(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    requestPlanWriteData
                )

                call.enqueue(object : Callback<ResponsePlanWriteData> {
                    override fun onResponse(
                        call: Call<ResponsePlanWriteData>,
                        response: Response<ResponsePlanWriteData>
                    ) {
                        if (response.code() == 200) {
                            Log.e("planWrite_server_test", "200")
                            Toast.makeText(requireActivity(), "작성되었습니다.", Toast.LENGTH_SHORT).show()
                            it.findNavController()
                                .navigate(R.id.action_planWriteFragment_to_diaryFragment)
                        }
                        else {
                            Log.e("planWrite_server_test", "codeFail")
                            Toast.makeText(requireActivity(), "계획 제목을 입력해주세요.", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<ResponsePlanWriteData>, t: Throwable) {
                        Log.e("planWrite_server_test", "fail")
                    }
                })

            }
        }
    }
}