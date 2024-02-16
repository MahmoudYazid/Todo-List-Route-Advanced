package com.yazid.advanced_todo.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yazid.advanced_todo.R


class Task_Item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_item)
        val scrollTaskView: TextView = findViewById(R.id.title)
        scrollTaskView.movementMethod = ScrollingMovementMethod.getInstance()

    }
}