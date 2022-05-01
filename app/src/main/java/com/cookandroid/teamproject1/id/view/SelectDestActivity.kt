package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.*
import com.cookandroid.teamproject1.databinding.SelectDestBinding

class SelectDestActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var selectRVAdapter: SelectRVAdapter
    private var dataList = mutableListOf<SelectDataModel>()
    private var selectdata = mutableListOf<SelectDataModel>()
    private var count : Int = 0

    private val selectDestArray : ArrayList<String> = arrayListOf()

    lateinit var binding: SelectDestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectDestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.selectDestGrid
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        selectRVAdapter = SelectRVAdapter(applicationContext)
        recyclerView.adapter = selectRVAdapter

        dataList.add(SelectDataModel("서울"))
        dataList.add(SelectDataModel("제주도"))
        dataList.add(SelectDataModel("경기도"))
        dataList.add(SelectDataModel("충청북도"))
        dataList.add(SelectDataModel("충청남도"))
        dataList.add(SelectDataModel("인천"))
        dataList.add(SelectDataModel("경상북도"))
        dataList.add(SelectDataModel("경상남도"))
        dataList.add(SelectDataModel("강원도"))
        dataList.add(SelectDataModel("전라북도"))
        dataList.add(SelectDataModel("전라남도"))
        dataList.add(SelectDataModel("기타"))

        selectRVAdapter.setDataList(dataList)

//        if (count==3){
//            binding.selectDestBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
//            binding.selectDestBtnConfirm.setTextColor(Color.WHITE)
//        }
//        recyclerView.setOnClickListener{
//            count = selectRVAdapter.getCountData()
//            Log.i("string", count.toString())
//
//            if (count==3){
//                binding.selectDestBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
//                binding.selectDestBtnConfirm.setTextColor(Color.WHITE)
//            }
//        }

        binding.selectDestBtnConfirm.setOnClickListener{
//            count = selectRVAdapter.getCountData()
//
//
//            Log.i("string", count.toString())

            selectdata = selectRVAdapter.getSelectData()
//            for (i in 0 until selectdata.size) {
//                Log.i("string", selectdata[i].title)
//            }
            for (i in 0 until selectdata.size) {
                selectDestArray.add(selectdata[i].title)
                Log.i("string", selectDestArray[i])
//                Log.i("string", selectdata[i].title)
            }

            val data = intent.getSerializableExtra("createAccountKey") as CreateAccountData
            val selectDestData = SelectDestData(
                data.idText, data.pwText,
                data.nicknameText, data.pNumText, selectDestArray)
//            startActivity(Intent(this, SelectThemeActivity::class.java))
//            Log.i("pNumText", data.pNumText)
//            Log.i("idText", data.idText)
//            Log.i("pwText", data.pwText)
//            Log.i("nicknameText", data.nicknameText)
//            Log.i("dest", selectDestArray.toString())




            val intent =Intent(this, SelectThemeActivity::class.java)
            intent.putExtra("selectDestKey", selectDestData)
            startActivity(intent)

        }
    }

    fun changeConfirmButton() {
        binding.selectDestBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
        binding.selectDestBtnConfirm.setTextColor(Color.WHITE)
    }
}


