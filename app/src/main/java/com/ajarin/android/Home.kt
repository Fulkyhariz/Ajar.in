package com.ajarin.android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.ajarin.android.Topics
import com.ajarin.android.dataHome
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView: View = inflater.inflate(R.layout.fragment_home, container, false)

        /*myView.law_button.setOnClickListener {
            val intent= Intent(activity,Topics::class.java)
                .putExtra(TOPICS_NAME,dataHome("Law", "", ""))
            startActivity(intent)
        }*/

        return myView
    }

    override fun onViewCreated(myView: View, savedInstanceState: Bundle?){
        super.onViewCreated(myView, savedInstanceState)
        val law_button = myView.findViewById<ImageButton>(R.id.law_button)

        law_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java))
            }
        }
    }

    companion object {
        const val TOPICS_NAME = ""
    }
}