package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajarin.android.`object`.DataHome
import com.ajarin.android.`object`.DataTutor
import com.ajarin.android.adapter.TopicsAdapter
import kotlinx.android.synthetic.main.activity_tutor_list.*

class TutorProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        val nameView : TextView = findViewById(R.id.prof_tutorName)
        val emailView : TextView = findViewById(R.id.prof_tutorEmail)
        val ratingView : TextView = findViewById(R.id.prof_rate)
        val subjectView : TextView = findViewById(R.id.prof_subjectName)
        val tarifView : TextView = findViewById(R.id.prof_price)

        val ss = intent.getSerializableExtra(TopicsAdapter.TOPICS) as DataTutor
        var tutorName = ss.nama
        var emailTutor = ss.emailtutor
        var pendidikan = ss.pendidikan
        var rating = ss.rating
        var subjectName = ss.subject
        var tarif = ss.tarif

        println(tutorName)
        println(emailTutor)
        println(pendidikan)
        println(rating)
        println(subjectName)
        println(tarif)

        nameView.text = tutorName
        emailView.text = emailTutor
        ratingView.text = rating
        subjectView.text = subjectName
        tarifView.text = tarif
    }
}