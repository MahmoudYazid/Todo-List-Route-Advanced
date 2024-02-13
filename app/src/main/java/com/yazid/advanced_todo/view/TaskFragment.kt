package com.yazid.advanced_todo.view

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yazid.advanced_todo.R
import com.yazid.advanced_todo.model.DateClass
import com.yazid.advanced_todo.model.Tasks_Info_Class
import com.yazid.advanced_todo.view.adaptors.DaysAdaptor
import com.yazid.advanced_todo.view.adaptors.TasksAdaptor
import com.yazid.advanced_todo.view.swipe_config.swipeToDelete
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TaskFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View_ =inflater.inflate(R.layout.fragment_task, container, false)
        // days recyclerView
        val Recycler_Days= View_.findViewById<RecyclerView>(R.id.DaysRecycler)
        Recycler_Days.adapter=DaysAdaptor(getNext30Days())



        // recyclerview Tasks


        val Recycler_Tasks= View_.findViewById<RecyclerView>(R.id.RecyclerViewTasks)

        val obj:Tasks_Info_Class=Tasks_Info_Class(
            date = "15-1-2022",
            task = "#1",
            done_state = "0"
        )
        val x:List<Tasks_Info_Class> = listOf(
            obj,obj,obj,obj,obj,obj,obj,obj
        )

        val swipeToDeleteCallback = swipeToDelete()
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(Recycler_Tasks)

        Recycler_Tasks.adapter=TasksAdaptor(x)














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
            calendar.time = parsedDate
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