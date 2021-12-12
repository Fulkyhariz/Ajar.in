package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView
import com.ajarin.android.`object`.DataTutor

class DetailedOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_order)

        val subject : TextView = findViewById(R.id.app_subject)
        val tutorName : TextView = findViewById(R.id.app_tutorName)
        val date : TextView = findViewById(R.id.app_date)
        val fee : TextView = findViewById(R.id.app_fee)
        val orderStatus : TextView = findViewById(R.id.order_status)

        val ss = intent.getSerializableExtra(BookingScreen.ORDER) as DataTutor

        var nama = ss.nama
        var subText = ss.subject
        var tanggal = ss.notelp
        var biaya = ss.tarif
        var statusOrder = "SUCCESS"

        subject.text = subText
        tutorName.text = nama
        date.text = tanggal
        fee.text = biaya
        orderStatus.text = statusOrder

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@DetailedOrder, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}