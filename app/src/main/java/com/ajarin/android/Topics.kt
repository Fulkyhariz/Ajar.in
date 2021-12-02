package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TopicsObject
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_topics.*

class Topics : AppCompatActivity() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    private var subjectList : ArrayList<TopicsObject> = arrayListOf()
    private lateinit var adapter: TopicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)
        val ss = intent.getSerializableExtra(Home.TOPICS_NAME) as DataHome
        val topicTitle: TextView = findViewById(R.id.topic_titles)
        topicTitle.text = ss.topicName

        var topicName = ss.topicName

        readSubject(subjectList, topicName)
        rv_subjectList.layoutManager = LinearLayoutManager(this)

    }

    private fun readSubject(list: ArrayList<TopicsObject>, topicName : String) : ArrayList<TopicsObject>{
        databaseReference?.child("topic")?.child(topicName).childre
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var topicList : TopicsObject ?= null
                    for (subject in snapshot.children) {
                        var subjectName : String = subject.child("Name").value.toString()
                        var desc : String = subject.child("Description").value.toString()
                        topicList = TopicsObject(topicName, subjectName, desc)
                        list.add(topicList)
                    }
                    adapter = TopicsAdapter(this@Topics,list)
                    rv_subjectList.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        return list
    }
}