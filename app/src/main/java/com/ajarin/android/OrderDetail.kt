package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.ajarin.android.`object`.DataHistory
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.adapter.HistoryAdapter
import com.ajarin.android.adapter.TopicsAdapter

class OrderDetail : AppCompatActivity() {

    companion object {
        const val TUTOR = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val subjectName : TextView = findViewById(R.id.mata_kuliah)
        val orderId : TextView = findViewById(R.id.det_orderID)
        val orderDate : TextView = findViewById(R.id.det_orderDate)
        val tutorName : TextView = findViewById(R.id.det_tutName)
        val totalPrice : TextView = findViewById(R.id.det_price)
        val ss = intent.getSerializableExtra(HistoryAdapter.HISTORY) as DataHistory

        subjectName.text = ss.subjectName
        orderId.text = ss.orderId
        orderDate.text = ss.orderDate
        tutorName.text = ss.tutorName
        totalPrice.text = ss.totalPrice

        val tutorBtn : ImageButton = findViewById(R.id.tutorButton)
        tutorBtn.setOnClickListener{
            val intent = Intent(this, TutorProfileHistory::class.java).putExtra(TUTOR, ss)
            startActivity(intent)
        }
    }
}