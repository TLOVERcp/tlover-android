package com.cookandroid.teamproject1.plan.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.teamproject1.R
import com.cookandroid.teamproject1.plan.model.PlanAcceptDataModel

class PlanAcceptRVAdapter(var context : Context) : RecyclerView.Adapter<PlanAcceptRVAdapter.Holder>() {

    var dataList = emptyList<PlanAcceptDataModel>()

    internal fun setDataList(planAcceptDataList : List<PlanAcceptDataModel>){
        this.dataList = planAcceptDataList
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var btn : Button = itemView.findViewById(R.id.item_plan_view_accept)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan_friend, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var data = dataList[position]
        holder.btn.text = data.title

    }

    override fun getItemCount(): Int {
        return  dataList.size
    }


}
