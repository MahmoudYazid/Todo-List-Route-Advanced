package com.yazid.advanced_todo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yazid.advanced_todo.R
import com.yazid.advanced_todo.repo.offline_resources.Room_Functions_Implementation
import com.yazid.advanced_todo.view_model.ViewModelGeneral
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //define fragments
        val optionsFragmentInst =optionFrgment()
        val taskFragmentInst =TaskFragment()

        // Open Task Fragment at First
        supportFragmentManager.beginTransaction()
            .replace(R.id.FragmentContainerViewItem,taskFragmentInst)
            .commit()

        // check if any clicks happened
        val NavigationBtmController = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationBtmController.setOnItemSelectedListener{ menu->
            when(menu.itemId){
                R.id.item1->{

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContainerViewItem,taskFragmentInst)
                        .commit()


                    true
                }
                R.id.item2->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FragmentContainerViewItem,optionsFragmentInst)
                        .commit()



                    true
                }
                else-> false

            }

        }

    }

}