package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.DataTutor
import com.ajarin.android.adapter.TopicsAdapter
import kotlinx.android.synthetic.main.activity_tutor_list.*
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.ContactsContract

import android.content.Intent
import android.widget.Toast

import android.app.Activity
import com.ajarin.android.adapter.TutorListAdapter

class TutorProfile : AppCompatActivity() {

    companion object {
        const val TUTOR = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        val nameView : TextView = findViewById(R.id.prof_tutorName)
        val emailView : TextView = findViewById(R.id.prof_tutorEmail)
        val ratingView : TextView = findViewById(R.id.prof_rate)
        val subjectView : TextView = findViewById(R.id.prof_subjectName)
        val tarifView : TextView = findViewById(R.id.txt_session)
        val contactButton : ImageButton = findViewById(R.id.contact_button)
        val appointmentButton : ImageButton = findViewById(R.id.appointment_button)

        val ss = intent.getSerializableExtra(TutorListAdapter.TUTOR) as DataTutor
        var tutorName = ss.nama
        var emailTutor = ss.emailtutor
        var pendidikan = ss.pendidikan
        var rating = ss.rating
        var subjectName = ss.subject
        var tarif = ss.tarif
        var phoneNumber = ss.notelp
        var tutorId = ss.id

        println(tutorName)
        println(emailTutor)
        println(pendidikan)
        println(rating)
        println(subjectName)
        println(tarif)
        println(tutorId)


        nameView.text = tutorName
        emailView.text = emailTutor
        ratingView.text = rating
        subjectView.text = subjectName
        tarifView.text = "$tarif /Session"

        contactButton.setOnClickListener{
            contactClicked(tutorName, emailTutor, phoneNumber)
        }
        appointmentButton.setOnClickListener{
            appointmentClicked(ss)
        }
    }

    private fun contactClicked(tutorName : String, emailTutor : String, phoneNumber : String){
        val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
        contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE
        contactIntent
            .putExtra(ContactsContract.Intents.Insert.NAME, tutorName)
            .putExtra(ContactsContract.Intents.Insert.EMAIL, emailTutor)
            .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber)
        startActivityForResult(contactIntent, 1)
    }

    private fun appointmentClicked(ss : DataTutor){
        val intent = Intent(this@TutorProfile, BookingScreen::class.java).putExtra(TUTOR, ss)
        startActivity(intent)
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