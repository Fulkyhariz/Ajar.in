package com.ajarin.android

import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*

class Profile : Fragment() {

    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        loadProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun loadProfile() {

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        user_email.text = user?.email

        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                user_name.text = snapshot.child("nama").value.toString()
                user_phone.text = snapshot.child("phone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


/*        logoutbutton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@Profile, Login::class.java))
            finish()
        }*/
    }
}