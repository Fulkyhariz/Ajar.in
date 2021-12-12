package com.ajarin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajarin.android.`object`.HistoryObject
import com.ajarin.android.`object`.TopicsObject
import com.ajarin.android.`object`.TutorObject
import com.ajarin.android.adapter.HistoryAdapter
import com.ajarin.android.adapter.TopicsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_history.*


class History : Fragment() {

    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var databaseReference :  DatabaseReference? = database?.reference
    lateinit var auth: FirebaseAuth
    private var historyList : ArrayList<HistoryObject> = arrayListOf()
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(myView: View, savedInstanceState: Bundle?){
        super.onViewCreated(myView, savedInstanceState)
        readHistory(myView, historyList)
        rv_history.layoutManager = LinearLayoutManager(myView.context)
    }

    private fun readHistory(myView: View, list: ArrayList<HistoryObject>) {
        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        databaseReference?.child("history")?.addListenerForSingleValueEvent(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var histories : HistoryObject?= null
                for (history in snapshot.children) {
                    if(history.child("userId").value.toString() == currentuser?.uid){
                        var orderId : String = history.key.toString()
                        var orderDate : String = "12 Desember 2021"
                        var orderStatus : String = "Success"
                        var subjectName : String = history.child("subject").value.toString()
                        var tutorName : String = history.child("nama").value.toString()
                        var tutorId : String = history.child("tutorId").value.toString()
                        var totalPrice : String = history.child("tarif").value.toString()
                        histories = HistoryObject(orderId, orderDate, orderStatus, subjectName, tutorName, tutorId, totalPrice)
                        list.add(histories)
                    }
                }
                adapter = HistoryAdapter(myView.context, list)
                rv_history.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}