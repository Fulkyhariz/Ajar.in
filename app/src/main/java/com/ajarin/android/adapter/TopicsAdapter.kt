package com.ajarin.android.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.R
import com.ajarin.android.Register
import com.ajarin.android.TutorList
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TopicsObject

class TopicsAdapter(private val parent : Context, private val subjectList : ArrayList<TopicsObject>) : RecyclerView.Adapter<TopicsAdapter.MyViewHolder>(){

    var posisi: Int? = null;

    companion object {
        const val TOPICS = ""
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
        val subjectName : TextView
        val subjectDesc : TextView
        init{
            subjectName = itemview.findViewById(R.id.subjectName)
            subjectDesc = itemview.findViewById(R.id.subjectDesc)
            itemview.setOnClickListener{
                val position: Int = adapterPosition
                posisi = adapterPosition
                val intent = Intent( parent, TutorList::class.java).putExtra(TOPICS, DataHome(subjectList[position].topicName.toString(), subjectList[position].subjectName.toString(),""))
                parent.startActivity(intent)
            }
        }
    }
}