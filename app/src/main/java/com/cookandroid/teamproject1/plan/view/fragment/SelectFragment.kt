package com.cookandroid.teamproject1.plan.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.FragmentSelectBinding
import com.cookandroid.teamproject1.plan.viewmodel.SelectViewModel

class SelectFragment : Fragment() {

    private var mBinding : FragmentSelectBinding?= null
    private var selectdata = mutableListOf<String>()
    private lateinit var sharedViewModel : SelectViewModel
    private var passText = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectBinding.inflate(inflater, container, false)
        mBinding = binding
        Log.e("", "viewCreated")

//        setFragmentResultListener("requestThirdKey") { requestKey, bundle ->
//            Log.e("listener", "기존셋팅")
//            passText = bundle.getString("existKey").toString()
//            Log.e("리스너불러오기", passText)
////            val bundle2 = bundleOf("passKey" to passText)
////            Log.e("리스너셋팅", passText)
////            setFragmentResult("requestSecondKey", bundle2)
//
//        }
        setFragmentResultListener("requestThirdKey") {requestKey, bundle ->
            Log.e("listener", "기존셋팅")
            val passText = bundle.getString("existKey").toString()
            Log.e("listener", passText)
        }


        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mBinding?.fragmentDiaryRegionSelectClose?.setOnClickListener(){

//            sharedViewModel = ViewModelProvider(this).get(SelectViewModel::class.java)
//            sharedViewModel.updateInputRegion(selectdata.toString())
            val bundle2 = bundleOf("passKey" to passText)
            setFragmentResult("requestSecondKey", bundle2)
//            activity?.supportFragmentManager?.popBackStack()
            val bundle = bundleOf("senderKey" to selectdata.toString())
            setFragmentResult("requestKey", bundle)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container2, PlanWriteFragment())
                ?.commit()
        }
        //                ?.replace(R.id.container2, PlanWriteFragment())
        // ?.hide(this)

        // 버튼 클릭 이벤트 모두

        val arrB : ArrayList<Button> = arrayListOf()
        arrB.add(mBinding?.button1!!)
        arrB.add(mBinding?.button2!!)
        arrB.add(mBinding?.button3!!)
        arrB.add(mBinding?.button4!!)
        arrB.add(mBinding?.button5!!)
        arrB.add(mBinding?.button6!!)
        arrB.add(mBinding?.button7!!)
        arrB.add(mBinding?.button8!!)
        arrB.add(mBinding?.button9!!)
        arrB.add(mBinding?.button10!!)
        arrB.add(mBinding?.button11!!)
        arrB.add(mBinding?.button12!!)
        arrB.add(mBinding?.button13!!)
        arrB.add(mBinding?.button14!!)
        arrB.add(mBinding?.button15!!)
        arrB.add(mBinding?.button16!!)
        arrB.add(mBinding?.button17!!)
        arrB.add(mBinding?.button18!!)
        arrB.add(mBinding?.button19!!)
        arrB.add(mBinding?.button20!!)
        arrB.add(mBinding?.button21!!)
        arrB.add(mBinding?.button22!!)
        arrB.add(mBinding?.button23!!)
        arrB.add(mBinding?.button24!!)
        arrB.add(mBinding?.button25!!)


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


//        mBinding?.button1?.setOnClickListener(){
////            if (selectdata.size==4){
////                return@setOnClickListener
////            }
//            for (i in 0 until selectdata.size) {
//                if (selectdata[i] == mBinding?.button1?.text as String){
//                selectdata.removeAt(i)
//                    mBinding?.button1?.setBackgroundResource(R.drawable.plan_select_region_background)
//                    mBinding?.button1?.setTextColor(Color.parseColor("#2E2E33"))
//                    return@setOnClickListener
//                }
//                }
//            selectdata.add(mBinding?.button1?.text.toString())
//                mBinding?.button1?.setBackgroundResource(R.drawable.backgroud_fragment_select_select)
//                mBinding?.button1?.setTextColor(Color.parseColor("#ffffff"))
//
//        }

}

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}