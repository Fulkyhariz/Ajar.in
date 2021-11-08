package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerbtn : TextView = findViewById(R.id.registerbtn)
        val loginbtn : ImageButton = findViewById(R.id.loginbutton)

        registerbtn.setOnClickListener{
            Intent( this@Login, Register::class.java).also{
                startActivity(it)
            }
        }

        loginbtn.setOnClickListener{
            Intent( this@Login, MainActivity::class.java).also{
                startActivity(it)
            }
        }
        /*loginbtn.setOnClickListener(

        )*/
    }
}