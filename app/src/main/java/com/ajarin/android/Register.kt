package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null

    val loginbtn : TextView = findViewById(R.id.loginbtn)
    val registerButton : ImageView = findViewById(R.id.registerbutton)
    val nameInput : EditText = findViewById(R.id.nameinput)
    val emailInput : EditText = findViewById(R.id.emailinput)
    val passwordInput : EditText = findViewById(R.id.passinput)
    val phoneInput : EditText = findViewById(R.id.phoneinput)
    val tutorInput : Spinner = findViewById(R.id.stuortuin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register() {

        registerButton.setOnClickListener {

            auth.createUserWithEmailAndPassword(emailInput.text.toString(), passwordInput.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("nama")?.setValue(nameInput.text.toString())
                        currentUSerDb?.child("phone")?.setValue(phoneInput.text.toString())
                        currentUSerDb?.child("tutor")?.setValue(tutorInput.selectedItem.toString())

                        Toast.makeText(this@Register, "Registration Success. ", Toast.LENGTH_LONG).show()
                        finish()

                    } else {
                        Toast.makeText(this@Register, "Registration failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}