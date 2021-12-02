package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TopicsObject
import com.ajarin.android.`object`.TutorObject
import com.ajarin.android.adapter.TopicsAdapter
import com.ajarin.android.adapter.TutorListAdapter
import com.google.firebase.database.*

class TutorList : AppCompatActivity() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    private var tutorList : ArrayList<String> = arrayListOf()
    private lateinit var adapter: TutorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)

        val ss = intent.getSerializableExtra(TopicsAdapter.TOPICS) as DataHome
        var topicName = ss.topicName
        var subjectName = ss.subjectName

        println(topicName)
        println(subjectName)
        getData(topicName, subjectName)

    }

    private fun getData(topicName : String, subjectName : String) {
        databaseReference?.child("topic")?.child(topicName)?.child(subjectName)?.child("tutorList")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tutor :String = snapshot.value.toString()
                    println(tutor)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}