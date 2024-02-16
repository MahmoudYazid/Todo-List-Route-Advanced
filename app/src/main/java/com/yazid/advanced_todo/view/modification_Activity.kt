package com.yazid.advanced_todo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yazid.advanced_todo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class modification_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modification)
    }
}