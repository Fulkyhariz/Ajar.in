package com.ajarin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment=Home()
        val secondFragment=Profile()
        val thirdFragment=History()

        setCurrentFragment(firstFragment)

        bottom_Nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home2->setCurrentFragment(firstFragment)
                R.id.profile->setCurrentFragment(secondFragment)
                R.id.history->setCurrentFragment(thirdFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }

}