package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajarin.android.`object`.TopicsObject
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.database.*

class Topics : AppCompatActivity() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    private lateinit var topicsRecycleView : RecyclerView
    private var subjectList : ArrayList<TopicsObject> = arrayListOf()
    private lateinit var adapter: TopicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)

        subjectList = readSubject(subjectList)
        topicsRecycleView = findViewById(R.id.rv_subjectList)
        adapter = TopicsAdapter(subjectList)
        topicsRecycleView.adapter = adapter
        topicsRecycleView.layoutManager =  LinearLayoutManager(this)
    }

    private fun readSubject(list: ArrayList<TopicsObject>) : ArrayList<TopicsObject>{
        databaseReference?.child("topic")?.child("IT & Development")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (subject in snapshot.children) {
                        val topicList = TopicsObject()
                        topicList.subjectName = subject.child("Name").value.toString()
                        topicList.subjectDesc = subject.child("Description").value.toString()
                        list.add(topicList)
                    }
                    println(list[0].subjectName)
                    println(list[0].subjectDesc)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        return list
    }
}