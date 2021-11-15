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
    private var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    var databaseReference :  DatabaseReference? = database?.reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        /*
        val privacyPolicybtn = myView.findViewById<TextView>(R.id.privacyPolicy)
        val termsofServicebtn = myView.findViewById<TextView>(R.id.termConditions)
        val logOutbtn = myView.findViewById<Button>(R.id.logoutbutton)*/

        auth = FirebaseAuth.getInstance()
        loadProfile()
        /*
        logOutbtn.setOnClickListener{
            auth.signOut()
            requireActivity().run {
                val intent = Intent(this, Login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        privacyPolicybtn.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, PrivacyPolicy::class.java))
            }
        }

        termsofServicebtn.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(getActivity(), TermsCondition::class.java))
            }
        }*/
        return myView
    }

    override fun onViewCreated(myView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(myView, savedInstanceState)
        val privacyPolicybtn = myView.findViewById<TextView>(R.id.privacyPolicy)
        val termsofServicebtn = myView.findViewById<TextView>(R.id.termConditions)
        val logOutbtn = myView.findViewById<ImageButton>(R.id.logoutbutton)
        logOutbtn.setOnClickListener{
            auth.signOut()
            requireActivity().run {
                val intent = Intent(this, Login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        privacyPolicybtn.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, PrivacyPolicy::class.java))
            }
        }

        termsofServicebtn.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(getActivity(), TermsCondition::class.java))
            }
        }
            // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private fun loadProfile() {

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        databaseReference?.child("profile")?.child(currentUser!!.uid)
            ?.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var map = snapshot.value as Map<String,Any>
                    user_name.text = map["nama"].toString()
                    user_phone.text = map["phone"].toString()
                    user_email.text = map["email"].toString()
                }
            })
    }
}