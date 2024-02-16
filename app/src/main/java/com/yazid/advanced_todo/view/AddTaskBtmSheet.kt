package com.yazid.advanced_todo.view

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
class AddTaskBtmSheet : BottomSheetDialogFragment(),DatePickerDialog.OnDateSetListener {
    var DatePickerData:String="Select a Data"
    private val viewmodel: ViewModelGeneral by viewModels()
    val coroutineScope = CoroutineScope(Dispatchers.IO)



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
        val TaskText= View_.findViewById<EditText>(R.id.TaskTextEditor)

        // add action btm
        val Btm= View_.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        Btm.setOnClickListener{
            coroutineScope.launch {
                viewmodel.Insert_vm(Tasks_Info_Class(
                    date =TextDatePickerInst.text.toString(),
                    task = TaskText.text.toString(),
                    done_state ="no",
                    id = null

                ))
                viewmodel.GetData_ViewModel()

                dismiss()


            }

        }

        return View_
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, Day: Int) {
        val monthM:Int  =month+1
        this.DatePickerData= "$Day-$monthM-$year"

        ChangeDataAfterPick(requireView())

    }
    private fun ChangeDataAfterPick(View_:View):Unit{
        val DataPickerInst=View_.findViewById<TextView>(R.id.DatePickerClickText)
        DataPickerInst.text=this.DatePickerData
    }


}