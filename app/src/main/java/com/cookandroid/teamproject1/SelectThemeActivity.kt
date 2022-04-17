package com.cookandroid.teamproject1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.SelectThemeBinding

class SelectThemeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private var dataList = mutableListOf<DataModel>()

    lateinit var binding: SelectThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.selectThemeGrid
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        customAdapter = CustomAdapter(applicationContext)
        recyclerView.adapter = customAdapter

        dataList.add(DataModel("액티비티"))
        dataList.add(DataModel("힐링"))
        dataList.add(DataModel("맛집투어"))
        dataList.add(DataModel("핫플"))
        dataList.add(DataModel("봄나들이"))
        dataList.add(DataModel("드라이브"))
        dataList.add(DataModel("가족과 함께"))
        dataList.add(DataModel("친구와 함께"))
        dataList.add(DataModel("연인과 함께"))
        dataList.add(DataModel("감성 여행"))
        dataList.add(DataModel("포토 스팟"))
        dataList.add(DataModel("유적지"))

        customAdapter.setDataList(dataList)

        binding.selectThemeBtnConfirm.setOnClickListener{
            startActivity(Intent(this,HomeActivity::class.java))
        }


    }
}


