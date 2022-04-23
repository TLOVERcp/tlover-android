package com.cookandroid.teamproject1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SelectRVAdapter(var context : Context) : RecyclerView.Adapter<SelectRVAdapter.Holder>() {

    var dataList = emptyList<SelectDataModel>()
    private var selectdata = mutableListOf<SelectDataModel>()
    var count : Int =0

    internal fun setDataList(selectDataList : List<SelectDataModel>){
        this.dataList = selectDataList
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var btn : Button = itemView.findViewById(R.id.grid_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var data = dataList[position]
        holder.btn.text = data.title

        holder.btn.setOnClickListener{
//            if(holder.btn.textColors == Color.parseColor("#FFFFFF")){
//
//            }
            count++
            if(count==3){
            }
            if (selectdata.size>=3){
                Toast.makeText(this.context, data.title, Toast.LENGTH_SHORT).show()
//                selectdata.removeAt(0)

//                holder.btn.isClickable = false
//                holder.btn.isEnabled = false
                return@setOnClickListener
            }

            holder.btn.setBackgroundResource(R.drawable.select_click)
            holder.btn.setTextColor(Color.parseColor("#FFFFFF"))
            selectdata.add(SelectDataModel(data.title))
        }
    }

    override fun getItemCount(): Int {
       return  dataList.size
    }


}