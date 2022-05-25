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
import androidx.annotation.RequiresApi
import androidx.fragment.app.setFragmentResultListener
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
class PlanWriteFragment : Fragment(){
    private var mBinding: FragmentPlanWriteBinding?=null
    private var inputText = ""
    private lateinit var sharedViewModel : SelectViewModel
    private var list : ArrayList<String> = arrayListOf()
    private var listed : List<String> = arrayListOf()

    // null 값 방지
    var isExpense : Boolean = false
    var isRegion : Boolean = false
    var issDate : Boolean = false
    var iseDate : Boolean = false
    var isTitle : Boolean = false
    var isContext : Boolean = false

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
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("senderKey").toString()
            val new = result.substring(1,result.length-1)
            listed = new.split(", ")
            for (i in listed.indices){
                list.add(listed[i])
            }
            mBinding?.fragmentPlanWriteLocationEt?.setText(new)
            isRegion=true
            mBinding?.fragmentPlanWriteLocationEt?.setTextColor(Color.parseColor("#2E2E33"))
        }
            return mBinding?.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding?.signUpingBackImg?.setOnClickListener(){
            it.findNavController().navigate(R.id.action_planWriteFragment_to_homeFragment)
        }
            // 지역 선택
            mBinding?.fragmentPlanWriteLocationEt?.setOnClickListener() {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container, SelectFragment(), "tag")
                    ?.addToBackStack(null)
                    ?.commit()
                mBinding?.fragmentPlanWriteLocationEt?.isEnabled = false

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

        mBinding?.fragmentPlanWriteCalendarImg?.setOnClickListener {


            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, myear, month, mdayOfMonth ->
                val mmonth2 = month + 1
                var m = ""
                m = if (mmonth2<10){
                    "0$mmonth2"
                }else{
                    mmonth2.toString()
                }
                var d = ""
                d = if (mdayOfMonth<10){
                    "0$mdayOfMonth"
                }else{
                    mdayOfMonth.toString()
                }
                mBinding?.fragmentPlanWriteDateEt?.setText("" + myear + "-" + m + "-" + d)
                issDate = true
                mBinding?.fragmentPlanWriteDateEt?.setTextColor(Color.BLACK)
            }, year, month, day)

            datePickerDialog.show()
        }

            mBinding?.fragmentPlanWriteCalendarEndImg?.setOnClickListener {
                val cal = Calendar.getInstance()
                val year = cal.get(Calendar.YEAR)
                val month = cal.get(Calendar.MONTH)
                val day = cal.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireActivity(),
                    DatePickerDialog.OnDateSetListener { view, myear, month, mdayOfMonth ->
                        val mmonth2 = month + 1
                        var m = ""
                        m = if (mmonth2<10){
                            "0$mmonth2"
                        }else{
                            mmonth2.toString()
                        }
                        var d = ""
                        d = if (mdayOfMonth<10){
                            "0$mdayOfMonth"
                        }else{
                            mdayOfMonth.toString()
                        }

                        iseDate =true
                        mBinding?.fragmentPlanWriteEndDateEt?.setText("" + myear + "-" + m + "-" + d)
                        mBinding?.fragmentPlanWriteEndDateEt?.setTextColor(Color.BLACK)
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }

        // api 연동
        mBinding?.fragmentPlanWriteSaveBt?.setOnClickListener(){
            if (mBinding?.fragmentDiaryContentTv?.text!=null){
                isContext=true
            }
            if ( mBinding?.fragmentPlanWritePayEt?.text!=null){
                isExpense=true
            }
            if (mBinding?.fragmentPlanWriteTitleEdittext?.text!=null){
                isTitle=true
            }
            if (!issDate||!iseDate||!isRegion||!isContext||!isContext||!isExpense){
                return@setOnClickListener
            }
            val requestPlanWriteData = RequestPlanWriteData(
                mBinding?.fragmentPlanWritePayEt?.text.toString().toLong(),
                mBinding?.fragmentDiaryContentTv?.text.toString(),
                mBinding?.fragmentPlanWriteEndDateEt?.text.toString()+" 00:00:00",
                mBinding?.fragmentPlanWriteDateEt?.text.toString()+" 23:59:59",
                list,
                mBinding?.fragmentPlanWriteTitleEdittext?.text.toString()
                )


        val call: Call<ResponsePlanWriteData> = ServiceCreator.planService.postPlanWrite(
            TloverApplication.prefs.getString("jwt", "null"),
            TloverApplication.prefs.getString("refreshToken", "null").toInt(),
            requestPlanWriteData)

        call.enqueue(object: Callback<ResponsePlanWriteData> {
            override fun onResponse(
                call: Call<ResponsePlanWriteData>,
                response: Response<ResponsePlanWriteData>
            ) {
                if(response.code() == 200){
                    Log.e("planWrite_server_test", "200")
                    it.findNavController().navigate(R.id.action_planWriteFragment_to_homeFragment)
                    //저장완료 표시라도?
                }
                else{
                    Log.e("planWrite_server_test", "codeFail")

            }
            }

            override fun onFailure(call: Call<ResponsePlanWriteData>, t: Throwable) {
                Log.e("planWrite_server_test", "fail")
            }
        })

        }
    }
}