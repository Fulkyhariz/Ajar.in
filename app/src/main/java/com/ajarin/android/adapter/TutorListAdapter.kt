package com.ajarin.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.R
import com.ajarin.android.`object`.TutorObject

class TutorListAdapter(private val parent : Context, private val tutorList : ArrayList<TutorObject>) : RecyclerView.Adapter<TutorListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tutorlist, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tutorList[position]

        holder.tutorName.text = currentItem.nama
        holder.tutorRating.text = currentItem.rating
    }

    override fun getItemCount(): Int {
        return tutorList.size
    }

    inner class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val tutorName : TextView
        val tutorRating : TextView
        init{
            tutorName = itemview.findViewById(R.id.list_tutorName)
            tutorRating = itemview.findViewById(R.id.list_rate)
            itemView.setOnClickListener{
                val position: Int = adapterPosition
                println(position)
            }
        }

    }
}