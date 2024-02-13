package com.yazid.advanced_todo.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yazid.advanced_todo.R
import java.util.Calendar


class AddTaskBtmSheet : BottomSheetDialogFragment(),DatePickerDialog.OnDateSetListener {
    var DatePickerData:String="Select a Data"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View_=inflater.inflate(R.layout.fragment_add_task_btm_sheet, container, false)
        // on click listner on date picker
        val TextDatePickerInst= View_.findViewById<TextView>(R.id.DatePickerClickText)
        TextDatePickerInst.setOnClickListener {
            val calender = Calendar.getInstance()
            val month = calender.get(Calendar.MONTH)
            val Day = calender.get(Calendar.DAY_OF_MONTH)
            val year = calender.get(Calendar.YEAR)

            val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, Day)
            datePickerDialog.datePicker.minDate = calender.timeInMillis

            datePickerDialog.show()
        }


        return View_
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, Day: Int) {

        this.DatePickerData= "$Day-$month-$year"

        ChangeDataAfterPick(requireView())

    }
    private fun ChangeDataAfterPick(View_:View):Unit{
        val DataPickerInst=View_.findViewById<TextView>(R.id.DatePickerClickText)
        DataPickerInst.text=this.DatePickerData
    }


}