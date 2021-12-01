package com.ajarin.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.R
import com.ajarin.android.`object`.TopicsObject

class TopicsAdapter(private val subjectList : ArrayList<TopicsObject>) : RecyclerView.Adapter<TopicsAdapter.MyViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: TopicsObject)

    }

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
        val subjectName : TextView = itemview.findViewById(R.id.subjectName)
        val subjectDesc : TextView = itemview.findViewById(R.id.subjectDesc)
    }
}