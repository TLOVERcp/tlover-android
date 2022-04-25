package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.SelectRVAdapter
import com.cookandroid.teamproject1.SelectDataModel
import com.cookandroid.teamproject1.FirstTitleActivity
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.databinding.CreateAccountBinding
import com.cookandroid.teamproject1.databinding.SelectThemeBinding
import com.cookandroid.teamproject1.id.model.RequestUserData
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel

class SelectThemeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var selectRVAdapter: SelectRVAdapter
    private var dataList = mutableListOf<SelectDataModel>()
    private var selectdata = mutableListOf<SelectDataModel>()

    private lateinit var sharedViewModel : SignUpViewModel

    lateinit var binding: SelectThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding = SelectThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.selectThemeGrid
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        selectRVAdapter = SelectRVAdapter(applicationContext)
        recyclerView.adapter = selectRVAdapter

        dataList.add(SelectDataModel("액티비티"))
        dataList.add(SelectDataModel("힐링"))
        dataList.add(SelectDataModel("맛집투어"))
        dataList.add(SelectDataModel("핫플"))
        dataList.add(SelectDataModel("봄나들이"))
        dataList.add(SelectDataModel("드라이브"))
        dataList.add(SelectDataModel("가족과 함께"))
        dataList.add(SelectDataModel("친구와 함께"))
        dataList.add(SelectDataModel("연인과 함께"))
        dataList.add(SelectDataModel("감성 여행"))
        dataList.add(SelectDataModel("포토 스팟"))
        dataList.add(SelectDataModel("유적지"))

        selectRVAdapter.setDataList(dataList)



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


