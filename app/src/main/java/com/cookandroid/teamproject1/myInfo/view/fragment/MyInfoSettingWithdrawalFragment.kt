package com.cookandroid.teamproject1.myInfo.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentMyInfoSettingBinding
import com.cookandroid.teamproject1.databinding.FragmentMyInfoSettingWithdrawalBinding
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.myInfo.model.RequestWithdrawData
import com.cookandroid.teamproject1.myInfo.model.ResponseLogoutData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoSettingWithdrawalFragment : Fragment(){
    private var mBinding : FragmentMyInfoSettingWithdrawalBinding?= null
    var radiobtck1: Boolean = false
    var radiobtck2: Boolean = false
    var radiobtck3: Boolean = false
    var radiobtck4: Boolean = false
    private var reason : ArrayList<String> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoSettingWithdrawalBinding.inflate(inflater, container, false)

        mBinding = binding

        mBinding?.settingWithdrawalBackImg?.setOnClickListener{
            val action = MyInfoSettingWithdrawalFragmentDirections.actionMyInfoSettingWithdrawalFragmentToMyInfoSettingFragment()
            it.findNavController().navigate(action)
        }

        val arrB : ArrayList<RadioButton> = arrayListOf()
        val radiobtck : ArrayList<Boolean> = arrayListOf()

        arrB.add(mBinding?.rBtn1!!)
        arrB.add(mBinding?.rBtn2!!)
        arrB.add(mBinding?.rBtn3!!)
        arrB.add(mBinding?.rBtn4!!)

        radiobtck.add(radiobtck1)
        radiobtck.add(radiobtck2)
        radiobtck.add(radiobtck3)
        radiobtck.add(radiobtck4)


        for (i in 0 until arrB.size){
            arrB[i].setOnClickListener(){
                if(radiobtck[i]) {
                    arrB[i].isChecked =false
                    radiobtck[i] = false
                    Log.e("", arrB[i].text.toString())
                }
                else{
                    radiobtck[i] = true
                    Log.e("", arrB[i].text.toString())
                }
            }
            }


        //회원탈퇴 버튼 클릭
        mBinding?.settingWithdrawalBtnDrawal?.setOnClickListener{
            for (i in 0 until arrB.size) {
                if (arrB[i].isChecked) {
                    reason.add(arrB[i].text.toString())
                }
            }
            val inputPassword = binding.settingWithdrawalPwEdit.text.toString()

            val requestWithdrawData = RequestWithdrawData(
                password = inputPassword
            )

            var builder = AlertDialog.Builder(context)
            builder.setTitle("정말 탈퇴하시겠어요?")
            builder.setMessage("탈퇴하시면 회원정보가 즉시 삭제돼요.")
            builder.setCancelable(false)
            builder.setPositiveButton("네", DialogInterface.OnClickListener { dialog, id ->
                val call: Call<ResponseLogoutData> = ServiceCreator.myInfoService.userWithdraw(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                    requestWithdrawData
                )
                call.enqueue(object : Callback<ResponseLogoutData> {
                    override fun onResponse(
                        call: Call<ResponseLogoutData>,
                        response: Response<ResponseLogoutData>
                    ) {
                        if (response.code() == 200) {
                            Log.e("reponse", "200!!~~~")
                            Toast.makeText(requireActivity(), "회원탈퇴 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            TloverApplication.prefs.setString("jwt", "null")
                            TloverApplication.prefs.setUserId("null")

                            val action = MyInfoSettingWithdrawalFragmentDirections.actionMyInfoSettingWithdrawalFragmentToSignInActivity()
                            it.findNavController().navigate(action)
                            dialog.cancel()
                        }
                        else if(response.code() == 400){
                            Toast.makeText(requireActivity(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLogoutData>, t: Throwable) {
                        Log.d(SignUpViewModel.TAG, "onFailure: $t")

                    }
                })
            })
            builder.setNegativeButton("취소", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            var alert: AlertDialog = builder.create()
            alert.show()
        }
        return mBinding?.root
    }

}