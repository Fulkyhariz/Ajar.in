package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.ajarin.android.`object`.DataTutor
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BookingScreen : AppCompatActivity() {

    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth

    companion object {
        const val ORDER = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_screen)

        val subject : TextView = findViewById(R.id.app_subject)
        val tutorName : TextView = findViewById(R.id.app_tutorName)
        val date : TextView = findViewById(R.id.app_date)
        val fee : TextView = findViewById(R.id.app_fee)
        val feeTotal : TextView = findViewById(R.id.tv_priceHistory)
        val bookingButton : ImageButton = findViewById(R.id.booking_button)

        val ss = intent.getSerializableExtra(TutorProfile.TUTOR) as DataTutor

        var nama = ss.nama
        var subText = ss.subject
        var biaya = ss.tarif
        var biayaTotal = ss.tarif

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val tanggal = formatter.format(Calendar.getInstance().time)

        subject.text = subText
        tutorName.text = nama
        date.text = tanggal
        fee.text = biaya
        feeTotal.text = biayaTotal


        bookingButton.setOnClickListener{
            uploadHistory(ss)
            val intent = Intent(this@BookingScreen, DetailedOrder::class.java).putExtra(ORDER, ss)
            startActivity(intent)
        }
    }

    private fun uploadHistory(ss: DataTutor) {
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("history")
        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        val orderId = (Math.random() * 9000).toInt() + 1000
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val tanggal = formatter.format(Calendar.getInstance().time)
        val currentUSerDb = databaseReference?.child(orderId.toString())
        currentUSerDb?.child("userId")?.setValue(currentuser?.uid)
        currentUSerDb?.child("tutorId")?.setValue(ss.id)
        currentUSerDb?.child("tanggal")?.setValue(tanggal)
        currentUSerDb?.child("nama")?.setValue(ss.nama)
        currentUSerDb?.child("email")?.setValue(ss.emailtutor)
        currentUSerDb?.child("notelp")?.setValue(ss.notelp)
        currentUSerDb?.child("pendidikan")?.setValue(ss.pendidikan)
        currentUSerDb?.child("rating")?.setValue(ss.rating)
        currentUSerDb?.child("subject")?.setValue(ss.subject)
        currentUSerDb?.child("tarif")?.setValue(ss.tarif)

        Toast.makeText(this@BookingScreen, "Order Success. ", Toast.LENGTH_LONG).show()
        finish()

    }
}