package com.yazid.advanced_todo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yazid.advanced_todo.R
import com.yazid.advanced_todo.model.DateClass
import com.yazid.advanced_todo.model.Tasks_Info_Class
import com.yazid.advanced_todo.repo.offline_resources.Dao
import com.yazid.advanced_todo.repo.offline_resources.Room_Functions_Implementation
import com.yazid.advanced_todo.view.adaptors.DaysAdaptor
import com.yazid.advanced_todo.view.adaptors.TasksAdaptor
import com.yazid.advanced_todo.view.interfaces.IDaysTasks
import com.yazid.advanced_todo.view.interfaces.ITasks_adaptor_Functions
import com.yazid.advanced_todo.view_model.ViewModelGeneral
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class TaskFragment : Fragment() {
    private val viewmodel:ViewModelGeneral by viewModels()
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewmodel.GetData_ViewModel()

            }
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewmodel.GetData_ViewModel()

            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View_ = inflater.inflate(R.layout.fragment_task, container, false)
        // days recyclerView

        val Recycler_Days = View_.findViewById<RecyclerView>(R.id.DaysRecycler)

        val DaysAdaptor_inst=DaysAdaptor(getNext30Days(),requireContext())
        DaysAdaptor_inst.IdaysTasksInst=object :IDaysTasks{
            override  fun GetTaskofDay(Date: String) {
                coroutineScope.launch {
                    viewmodel.GetAllTasksInThisDay_ViewModel(Date)

                }
            }


        }
        Recycler_Days.adapter = DaysAdaptor_inst


        // recyclerview Tasks


        val Recycler_Tasks = View_.findViewById<RecyclerView>(R.id.RecyclerViewTasks)



        val TasksAdaptorInst = TasksAdaptor()

        //getData


        viewmodel.LiveDataTasks.observe(viewLifecycleOwner, Observer { tasks ->
            TasksAdaptorInst.SetData(tasks)
        })



        // implement functions in adaptor
        TasksAdaptorInst.InterfaceFunctions=object :ITasks_adaptor_Functions{
            override fun Delete_Item_in_Adaptor(Task:Tasks_Info_Class) {
                coroutineScope.launch {
                    viewmodel.Delete_vm(Task)

                }
            }

            override fun Go_T_modify_Item_in_Adaptor(Task: Tasks_Info_Class) {
                val Intent =Intent(requireContext(),modification_Activity::class.java)
                Intent.putExtra("Task",Task)
                requireContext().startActivity(Intent)
            }

        }


        Recycler_Tasks.adapter = TasksAdaptorInst


        // delete item in adaptor













        // add bottom fragment sheet
        val FloatActionInst = View_.findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        val TargetBtmfragmentInst = AddTaskBtmSheet()
        FloatActionInst.setOnClickListener{
            childFragmentManager.let {
                TargetBtmfragmentInst.show(it,null)
            }
        }

        return View_
    }

    fun getNext30Days(): List<DateClass> {
        val dateList = mutableListOf<DateClass>()
        val calendar = Calendar.getInstance()

        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())

        for (i in 0 until 30) {
            val dateString = dateFormat.format(calendar.time)
            val parsedDate = dateFormat.parse(dateString)

            // Extract day, month, and year components from parsedDate using Calendar
            if (parsedDate != null) {
                calendar.time = parsedDate
            }
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1 // Month starts from 0
            val year = calendar.get(Calendar.YEAR)

            val newDateClassInst = DateClass(
                Day = dayOfMonth.toString(),
                Month = month.toString(),
                Year = year.toString()
            )

            dateList.add(newDateClassInst)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return dateList
    }

}