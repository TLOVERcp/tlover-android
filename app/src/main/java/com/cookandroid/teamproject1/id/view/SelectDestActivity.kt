package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.CustomAdapter
import com.cookandroid.teamproject1.DataModel
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.SelectDestBinding

class SelectDestActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private var dataList = mutableListOf<DataModel>()

    lateinit var binding: SelectDestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectDestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.selectDestGrid
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        customAdapter = CustomAdapter(applicationContext)
        recyclerView.adapter = customAdapter

        dataList.add(DataModel("서울"))
        dataList.add(DataModel("제주도"))
        dataList.add(DataModel("경기도"))
        dataList.add(DataModel("충청북도"))
        dataList.add(DataModel("충청남도"))
        dataList.add(DataModel("인천"))
        dataList.add(DataModel("경상북도"))
        dataList.add(DataModel("경상남도"))
        dataList.add(DataModel("강원도"))
        dataList.add(DataModel("전라북도"))
        dataList.add(DataModel("전라남도"))
        dataList.add(DataModel("기타"))

        customAdapter.setDataList(dataList)

        binding.selectDestBtnConfirm.setOnClickListener{
            startActivity(Intent(this, SelectThemeActivity::class.java))
        }
    }

    fun changeConfirmButton() {
        binding.selectDestBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
        binding.selectDestBtnConfirm.setTextColor(Color.WHITE)
    }
}


