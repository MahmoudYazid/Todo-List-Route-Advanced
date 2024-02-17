package com.yazid.advanced_todo.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yazid.advanced_todo.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
@AndroidEntryPoint
class optionFrgment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // define first spinner
        val View =inflater.inflate(R.layout.fragment_option_frgment, container, false)






        // define second spinner

        val spinner_mode = View.findViewById<Spinner>(R.id.ModeSpinner)
        val Spinner_mode_adaptor= ArrayAdapter.createFromResource(requireContext(),R.array.modes,android.R.layout.simple_spinner_item)
        Spinner_mode_adaptor.setDropDownViewResource(android.R.layout.simple_spinner_item)

        spinner_mode.adapter=Spinner_mode_adaptor


        // make Functions of Lists
        // Mode List Function
        var ModeNow= ""
        spinner_mode.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val SelectedItem = p0?.getItemAtPosition(p2).toString()
                if (SelectedItem.equals("Dark mode")){
                    ModeNow = "Dark"
                }else{
                    ModeNow = "Light"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }













        //  Float ActionBtm
        val FLoatBm =View.findViewById<FloatingActionButton>( R.id.floatingActionButton)
        FLoatBm.setOnClickListener {
            if (ModeNow.equals("Dark")){
                ConvertToDarkMode()
            }else{
                ConvertToLightMode()
            }



        }

        return View
    }
    public fun ConvertToDarkMode():Unit {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


    }
    public fun ConvertToLightMode():Unit {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


    }




}