package com.yazid.advanced_todo.repo.offline_resources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yazid.advanced_todo.model.Tasks_Info_Class

@Dao
interface Dao {
    @Query("SELECT * FROM tasksDb")
    suspend fun GetAllTasks_dao():List<Tasks_Info_Class>

    @Delete
    fun deleteTasks_dao(TASK: Tasks_Info_Class)

    @Insert
    fun InsertTasks_dao(TASK: Tasks_Info_Class)

    @Query("UPDATE tasksDb SET task=:newTask WHERE task = :oldtask")
    fun ModifyTasks_dao(
        oldtask: String,

        newTask:String)


    @Query("UPDATE tasksDb SET date=:newDate WHERE date = :olddate")
    fun ModifyDate_dao(
        olddate: String,

        newDate:String)
}


