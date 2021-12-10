package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TopicsObject
import com.ajarin.android.`object`.TutorObject
import com.ajarin.android.adapter.TopicsAdapter
import com.ajarin.android.adapter.TutorListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_topics.*
import kotlinx.android.synthetic.main.activity_tutor_list.*

class TutorList : AppCompatActivity() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    private var tutorList : ArrayList<TutorObject> = arrayListOf()
    private lateinit var adapter: TutorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)

        val ss = intent.getSerializableExtra(TopicsAdapter.TOPICS) as DataHome
        var topicName = ss.topicName
        var subjectName = ss.subjectName

        println(topicName)
        println(subjectName)

        readTutor(tutorList, topicName, subjectName)
        rv_tutorList.layoutManager = LinearLayoutManager(this)

    }

    private fun readTutor(list: ArrayList<TutorObject>, topicName : String, subjectName : String){
        databaseReference?.child("topic")?.child(topicName)?.child(subjectName)?.child("tutorList")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var tutors : TutorObject ?= null
                    for (tutor in snapshot.children) {
                        var tutorName : String = tutor.child("Name").value.toString()
                        var tutorRating : String = tutor.child("Rating").value.toString()
                        var id : String = tutor.child("Id").value.toString()
                        tutors = TutorObject(tutorName, tutorRating, subjectName)
                        list.add(tutors)
                        readOther(tutors, tutor.child("Id").value.toString());
                    }
                    adapter = TutorListAdapter(this@TutorList,list)
                    rv_tutorList.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }

    private fun readOther(tutor: TutorObject, id : String){
        databaseReference?.child("profile")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                        for (profile in snapshot.children) {
                            if (profile.key == id){
                                println(id)
                                profile.child("email").value.toString()
                                println(profile.child("email").value.toString())
                                tutor.emailtutor = profile.child("email").value.toString()
                                tutor.tarif = profile.child("price").value.toString()
                                tutor.notelp = profile.child("phone").value.toString()
                                println(tutor.emailtutor)
                                println("line ini jalan")
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}