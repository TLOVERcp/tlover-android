package com.cookandroid.teamproject1.id.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.*
import com.cookandroid.teamproject1.databinding.SelectThemeBinding
import com.cookandroid.teamproject1.id.model.RequestUserData
import com.cookandroid.teamproject1.id.model.ResponseUserData
import com.cookandroid.teamproject1.id.model.SelectDataModel
import com.cookandroid.teamproject1.id.model.SelectDestData
import com.cookandroid.teamproject1.id.view.adapter.SelectRVAdapter
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectThemeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var selectRVAdapter: SelectRVAdapter
    private var dataList = mutableListOf<SelectDataModel>()
    private var selectdata = mutableListOf<SelectDataModel>()

    private val selectThemeArray : ArrayList<String> = arrayListOf()

    private lateinit var sharedViewModel: SignUpViewModel

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

        binding.selectThemeBtnConfirm.setOnClickListener {

            selectdata = selectRVAdapter.getSelectData()
            for (i in 0 until selectdata.size) {
                selectThemeArray.add(selectdata[i].title)
//                Log.i("string", selectdata[i].title)
            }

            val data = intent.getSerializableExtra("selectDestKey") as SelectDestData
//            val selectThemeData = SelectThemeData(
//                data.idText,
//                data.pwText,
//                data.nicknameText,
//                data.pNumText,
//                data.destArray,
//                selectThemeArray
//            )
//            startActivity(Intent(this, SelectThemeActivity::class.java))
            val intent = Intent(this, FirstTitleActivity::class.java)
//            intent.putExtra("selectThemeKey", selectThemeData)

            /**
             * 작성자: 원도혜
             * signUpService 회원가입 API 연결
             */

            val requestUserData = RequestUserData(
                data.idText,
                data.pwText,
                data.nicknameText,
                data.pNumText,
                data.destArray,
                selectThemeArray
            )

            val call: Call<ResponseUserData> = ServiceCreator.signUpService.postSignUp(requestUserData)

            call.enqueue(object: Callback<ResponseUserData> {
                override fun onResponse(
                    call: Call<ResponseUserData>,
                    response: Response<ResponseUserData>
                ) {
                    if(response.code() == 200){
                        Log.e("signup_server_test", "200")
                        TloverApplication.prefs.setString("message", response.body()?.message.toString())
                    }
                    else{
                        Log.e("signup_server_test", "responseFail")
                    }
                }

                override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                    Log.e("signup_server_test", "fail")
                }
            })

//            requestUserData.userId = sharedViewModel.getA().toString()

//            println(sharedViewModel.getA())
//            println("gg")

            startActivity(intent)

        }



        fun changeConfirmButton() {
            binding.selectThemeBtnConfirm.setBackgroundResource(R.drawable.confirm_btn_background_clicked)
            binding.selectThemeBtnConfirm.setTextColor(Color.WHITE)
        }

    }

}


