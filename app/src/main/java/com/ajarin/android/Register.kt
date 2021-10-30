package com.ajarin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginbtn : TextView = findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener{
            Intent( this@Register, Login::class.java).also{
                startActivity(it)
                finish()
            }
        }
    }
}