package com.yazid.advanced_todo.view

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yazid.advanced_todo.R
import com.yazid.advanced_todo.model.Tasks_Info_Class
import com.yazid.advanced_todo.view_model.ViewModelGeneral
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class modification_Activity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var DatePickerData:String="Select a Data"

    private val viewmodel: ViewModelGeneral by viewModels()
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modification)
        val Intent_input = intent.getSerializableExtra("Task") as Tasks_Info_Class

        //put old data
        val desc : EditText = findViewById(R.id.DescSpinner)
        desc.setText(Intent_input.task.toString())

        // set Data
        val Date : TextView = findViewById(R.id.DatePicker)
        Date.setText(Intent_input.date.toString())
        //save old date and task

        // pic new date
        Date.setOnClickListener {
            val calender = Calendar.getInstance()
            val month = calender.get(Calendar.MONTH)
            val Day = calender.get(Calendar.DAY_OF_MONTH)
            val year = calender.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(this, this, year, month, Day)
            datePickerDialog.datePicker.minDate = calender.timeInMillis
            datePickerDialog.datePicker.setBackgroundColor(Color.WHITE);

            datePickerDialog.show()



        }

        val Btm= findViewById<FloatingActionButton>(R.id.floatingActionButton)
        Btm.setOnClickListener{

                coroutineScope.launch {
                    viewmodel.modifyTask_vm(
                        NewDate_ = Date.text.toString(),
                        NewTask_ =desc.text.toString(),
                        oldTask_ =Intent_input.task.toString(),
                        Olddate_=Intent_input.date.toString())
                }




        }



    }

    override fun onDateSet(p0: DatePicker?,  year: Int, month: Int, Day: Int) {
        val monthM:Int  =month+1
        this.DatePickerData= "$Day-$monthM-$year"


        ChangeDataAfterPick()
    }
    private fun ChangeDataAfterPick():Unit{
        val DataPickerInst=findViewById<TextView>(R.id.DatePicker)
        DataPickerInst.text=this.DatePickerData
    }

}