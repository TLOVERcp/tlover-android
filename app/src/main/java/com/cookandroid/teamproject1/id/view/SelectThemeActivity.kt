package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.CustomAdapter
import com.cookandroid.teamproject1.DataModel
import com.cookandroid.teamproject1.FirstTitleActivity
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.CreateAccountBinding
import com.cookandroid.teamproject1.databinding.SelectThemeBinding
import com.cookandroid.teamproject1.id.model.RequestUserData
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel

class SelectThemeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private var dataList = mutableListOf<DataModel>()

    private lateinit var sharedViewModel : SignUpViewModel

    lateinit var binding: SelectThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
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
            val requestUserData = RequestUserData(
                userId = "",
                userPw = "",
                userNickname = "",
                userPhone = "",
                userReg = "인천",
                userThemaName = "",
            )

//            requestUserData.userId = sharedViewModel.getA().toString()

//            println(sharedViewModel.getA())
//            println("gg")

            startActivity(Intent(this, FirstTitleActivity::class.java))
        }


    }
    fun changeConfirmButton() {
        binding.selectThemeBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
        binding.selectThemeBtnConfirm.setTextColor(Color.WHITE)
    }

}


