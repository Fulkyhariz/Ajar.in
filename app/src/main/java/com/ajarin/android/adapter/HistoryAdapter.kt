package com.ajarin.android.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.OrderDetail
import com.ajarin.android.R
import com.ajarin.android.`object`.DataHistory
import com.ajarin.android.`object`.HistoryObject

class HistoryAdapter(private val parent: Context, private val historyList: ArrayList<HistoryObject>) : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>(){

    companion object {
        const val HISTORY = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = historyList[position]

        holder.orderId.text = currentitem.orderId
        holder.orderDate.text = currentitem.orderDate
        holder.orderStatus.text = currentitem.orderStatus
        holder.subjectName.text = currentitem.subjectName
        holder.tutorName.text = currentitem.tutorName
        holder.totalPrice.text = currentitem.totalPrice

    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val orderId : TextView
        val orderDate : TextView
        val orderStatus : TextView
        val subjectName : TextView
        val tutorName : TextView
        val totalPrice : TextView
        init{
            orderId = itemview.findViewById(R.id.tv_orderID)
            orderDate = itemview.findViewById(R.id.tv_orderDate)
            orderStatus = itemview.findViewById(R.id.tv_orderStat)
            subjectName = itemview.findViewById(R.id.tv_subjectName)
            tutorName = itemview.findViewById(R.id.tv_tutorName)
            totalPrice = itemview.findViewById(R.id.tv_priceHistory)
            itemview.setOnClickListener{
                val position: Int = adapterPosition
                    val intent = Intent( parent, OrderDetail::class.java).putExtra(HISTORY, DataHistory(
                        historyList[position].orderId.toString(), historyList[position].orderDate.toString(),
                        historyList[position].orderStatus.toString(), historyList[position].subjectName.toString(),
                        historyList[position].tutorName.toString(), historyList[position].tutorId.toString(),
                        historyList[position].tutorRating.toString(), historyList[position].totalPrice.toString()))
                    parent.startActivity(intent)
            }
        }
    }


}