package com.ajarin.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.R
import com.ajarin.android.`object`.TopicsObject

class TopicsAdapter(private val subjectList : ArrayList<TopicsObject>) : RecyclerView.Adapter<TopicsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_topics, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = subjectList[position]

        holder.subjectName.text = currentitem.subjectName
        holder.subjectDesc.text = currentitem.subjectDesc

    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    inner class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val subjectName : TextView
        val subjectDesc : TextView
        init{
            subjectName = itemview.findViewById(R.id.subjectName)
            subjectDesc = itemview.findViewById(R.id.subjectDesc)
            itemview.setOnClickListener{
                val position: Int = adapterPosition
                println(position)
            }
        }
    }
}