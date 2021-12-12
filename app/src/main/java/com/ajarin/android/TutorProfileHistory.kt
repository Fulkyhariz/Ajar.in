package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ajarin.android.`object`.DataHistory
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TutorObject
import com.ajarin.android.adapter.HistoryAdapter
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.database.*

class TutorProfileHistory : AppCompatActivity() {

    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    val tutorName : TextView = findViewById(R.id.prof_tutorName)
    val tutorEmail : TextView = findViewById(R.id.prof_tutorEmail)
    val tutorDesc : TextView = findViewById(R.id.prof_tutorDesc)
    val subjectName: TextView = findViewById(R.id.prof_subjectName)
    val price : TextView = findViewById(R.id.prof_price)
    val rating : TextView = findViewById(R.id.prof_rate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile_history)

        val ss = intent.getSerializableExtra(OrderDetail.TUTOR) as DataHistory
        val tutorId : String = ss.tutorId
        subjectName.text = ss.subjectName
        tutorDesc.text = "Becoming Tutor Since 2021"

        readTutor(tutorId)

    }

    private fun readTutor(tutorId : String) {
        databaseReference?.child("profile")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (profile in snapshot.children) {
                        if (profile.key == tutorId){
                            tutorName.text = profile.child("nama").value.toString()
                            tutorEmail.text = profile.child("email").value.toString()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

}