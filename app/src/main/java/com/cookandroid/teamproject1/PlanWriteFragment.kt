package com.cookandroid.teamproject1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cookandroid.teamproject1.databinding.FragmentPlanWriteBinding
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.google.android.material.datepicker.MaterialDatePicker



/**
 * 계획작성 프래그먼트
 */
class PlanWriteFragment : Fragment(){
    lateinit var binding: FragmentPlanWriteBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanWriteBinding.inflate(inflater, container, false)

        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signUpingBackImg.setOnClickListener(){
            it.findNavController().navigate(R.id.action_planWriteFragment_to_homeFragment)
        }


        binding.fragmentPlanWriteCalendarImg.setOnClickListener {


            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)+1
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                binding.fragmentPlanWriteDateEt.setText(""+ mdayOfMonth +"/"+ mmonth+ "/"+ myear)
            }, year, month, day)
            datePickerDialog.show()
        }


    }




}