package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.ajarin.android.`object`.DataHistory
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.TutorObject
import com.ajarin.android.adapter.HistoryAdapter
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.database.*

class TutorProfileHistory : AppCompatActivity() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    var phoneNumber : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile_history)

        var tutorName : TextView = findViewById(R.id.prof_tutorName)
        var tutorEmail : TextView = findViewById(R.id.prof_tutorEmail)
        var tutorDesc : TextView = findViewById(R.id.prof_tutorDesc)
        var subjectName: TextView = findViewById(R.id.prof_subjectName)
        var price : TextView = findViewById(R.id.prof_price)
        var rating : TextView = findViewById(R.id.prof_rate)
        var contactBtn : ImageButton = findViewById(R.id.contact_button2)
        val ss = intent.getSerializableExtra(OrderDetail.TUTOR) as DataHistory
        val tutorId : String = ss.tutorId
        subjectName.text = ss.subjectName
        tutorDesc.text = "Becoming Tutor Since 2021"
        rating.text = ss.tutorRating

        databaseReference?.child("profile")
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (profile in snapshot.children) {
                        if (profile.key == tutorId){
                            var tutName : String = profile.child("nama").value.toString()
                            tutorName.text = tutName
                            var tutEmail : String = profile.child("email").value.toString()
                            tutorEmail.text = tutEmail
                            var tutPrice = profile.child("price").value.toString()
                            price.text = tutPrice
                            phoneNumber = profile.child("phone").value.toString()
                            contactBtn.setOnClickListener{
                                val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
                                contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE
                                contactIntent
                                    .putExtra(ContactsContract.Intents.Insert.NAME, tutName)
                                    .putExtra(ContactsContract.Intents.Insert.EMAIL, tutEmail)
                                    .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber)
                                startActivityForResult(contactIntent, 1)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show()
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(
                    this, "Cancelled Added Contact",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}