package com.cookandroid.teamproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.databinding.SelectDestBinding

class SelectDestActivity : AppCompatActivity() {

    lateinit var binding: SelectDestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectDestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridView = binding.selectDestGrid
//        var adapter = CustomAdapter()

        gridView.adapter

    }
}

//class CustomAdapter : RecyclerView.Adapter<Holder>() {
//
//    var listData = arrayListOf("서울", "제주도", "경기도", "충청북도", "충청남도", "인천", "경상북도", "경상남도", "강원도", "전라북도", "전라남도", "기타")
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_list, parent, false)
//        return Holder(view)
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val data = listData.get(position)
//
//    }
//
//    override fun getItemCount(): Int {
//       return  listData.size
//    }
//
//
//}
////
//class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
//}
