package com.cookandroid.teamproject1.myInfo.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentMyInfoSettingBinding
import com.cookandroid.teamproject1.id.viewmodel.SignUpViewModel
import com.cookandroid.teamproject1.myInfo.model.ResponseLogoutData
import com.cookandroid.teamproject1.plan.model.ResponsePlanWriteData
import com.cookandroid.teamproject1.util.ServiceCreator
import com.cookandroid.teamproject1.util.TloverApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoSettingFragment : Fragment() {
    private var mBinding: FragmentMyInfoSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyInfoSettingBinding.inflate(inflater, container, false)

        mBinding = binding

        //회원탈퇴 버튼 클릭
        mBinding?.settingContentWithdrawal?.setOnClickListener {
            val action =
                MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToMyInfoSettingWithdrawalFragment()
            it.findNavController().navigate(action)
        }

        //뒤로가기 버튼 클릭
        mBinding?.settingBackImg?.setOnClickListener {
            val action =
                MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToMyInfoFragment()
            it.findNavController().navigate(action)
        }

        //이용약관 클릭
        mBinding?.settingContentTermService?.setOnClickListener{
            val action = MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToMyInfoServiceFragment()
            it.findNavController().navigate(action)
        }

        //개인정보 처리방침 클릭
        mBinding?.settingContentPrivinfo?.setOnClickListener{
            val action = MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToMyInfoPrivacyFragment()
            it.findNavController().navigate(action)
        }

        //버전정보 클릭
        mBinding?.settingContentVersinfo?.setOnClickListener{
            val action = MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToMyInfoVersionFragment()
            it.findNavController().navigate(action)
        }

        //문의하기 클릭
        mBinding?.settingContentInquire?.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/unnEebxGofNLUZmZ9"))
            startActivity(intent)
        }

        //로그아웃 버튼 클릭
        mBinding?.settingContentLogout?.setOnClickListener {
            var builder = AlertDialog.Builder(context)
            builder.setTitle("로그아웃 하시겠어요?")
            builder.setCancelable(false)
            builder.setPositiveButton("네", DialogInterface.OnClickListener { dialog, id ->
                val call: Call<ResponseLogoutData> = ServiceCreator.myInfoService.getLogout(
                    TloverApplication.prefs.getString("jwt", "null"),
                    TloverApplication.prefs.getString("refreshToken", "null").toInt(),
                )
                call.enqueue(object : Callback<ResponseLogoutData> {
                    override fun onResponse(
                        call: Call<ResponseLogoutData>,
                        response: Response<ResponseLogoutData>
                    ) {
                        if (response.code() == 200) {
                            Log.e("reponse", "200!!~~~")
                            Toast.makeText(requireActivity(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                            TloverApplication.prefs.setString("jwt", "null")
                            TloverApplication.prefs.setUserId("null")

                            val action = MyInfoSettingFragmentDirections.actionMyInfoSettingFragmentToSignInActivity()
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