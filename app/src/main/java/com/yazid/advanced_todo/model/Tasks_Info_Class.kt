package com.yazid.advanced_todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("tasksDb")
data class Tasks_Info_Class(
    @PrimaryKey (autoGenerate = true) val id:Int?,
    val date:String,
    val task:String,
    val done_state:String
): Serializable
