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
    @Query("SELECT * FROM tasksDb WHERE date=:InputDate")
    suspend fun GetAllTasksInThisDay_dao(InputDate:String):List<Tasks_Info_Class>

    @Delete
    suspend fun deleteTasks_dao(TASK: Tasks_Info_Class)

    @Insert
    suspend fun InsertTasks_dao(TASK: Tasks_Info_Class)

    @Query("UPDATE tasksDb SET task = :newTask, date = :newDate WHERE task = :oldTask AND date = :oldDate")
    fun ModifyTasks_dao(newTask: String, newDate: String, oldTask: String, oldDate: String)



}


