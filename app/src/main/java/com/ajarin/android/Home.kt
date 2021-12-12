package com.ajarin.android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.ajarin.android.`object`.DataHome

class Home : Fragment() {
    companion object {
        const val TOPICS_NAME = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView: View = inflater.inflate(R.layout.fragment_home, container, false)


        return myView
    }

    override fun onViewCreated(myView: View, savedInstanceState: Bundle?){
        super.onViewCreated(myView, savedInstanceState)
        val law_button = myView.findViewById<ImageButton>(R.id.law_button)
        val business_button = myView.findViewById<ImageButton>(R.id.business_button)
        val it_button = myView.findViewById<ImageButton>(R.id.it_button)
        val medical_button = myView.findViewById<ImageButton>(R.id.medical_button)
        val humanities_button = myView.findViewById<ImageButton>(R.id.humanities_button)
        val engineering_button = myView.findViewById<ImageButton>(R.id.engineer_button)

        law_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("Law", "", "")
                ))
            }
        }

        business_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("Business", "", "")
                ))
            }
        }

        it_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("IT & Development", "", "")
                ))
            }
        }

        medical_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("Medical", "", "")
                ))
            }
        }

        humanities_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("Humanities", "", "")
                ))
            }
        }

        engineering_button.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, com.ajarin.android.Topics::class.java).putExtra(
                    TOPICS_NAME, DataHome("Engineering", "", "")
                ))
            }
        }

    }

}