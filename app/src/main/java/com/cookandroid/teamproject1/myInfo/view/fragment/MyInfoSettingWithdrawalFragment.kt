package com.cookandroid.teamproject1.myInfo.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        //회원탈퇴 버튼 클릭
        mBinding?.settingWithdrawalBtnDrawal?.setOnClickListener{
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

                            val action = MyInfoSettingWithdrawalFragmentDirections.actionMyInfoSettingWithdrawalFragmentToFirstTitleActivity()
                            it.findNavController().navigate(action)
                            dialog.cancel()
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