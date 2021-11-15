package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.content.Intent
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerbtn : TextView = findViewById(R.id.registerbtn)
        val loginbtn : ImageButton = findViewById(R.id.loginbutton)
        val emailInput : EditText = findViewById(R.id.emailinput)
        val passwordInput : EditText = findViewById(R.id.passinput)

        registerbtn.setOnClickListener{
            Intent( this@Login, Register::class.java).also{
                startActivity(it)
            }
        }

        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@Login, MainActivity::class.java))
            finish()
        }

        loginbtn.setOnClickListener{
            if(TextUtils.isEmpty(emailInput.text.toString())){
                emailInput.setError("Please enter username")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(passwordInput.text.toString())){
                emailInput.setError("Please enter password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailInput.text.toString(), passwordInput.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        startActivity(Intent(this@Login, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@Login, "Login failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}