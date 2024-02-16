package com.yazid.advanced_todo.repo.offline_resources

import com.yazid.advanced_todo.model.Tasks_Info_Class
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


public class Room_Functions_Implementation @Inject constructor( public  val DaoInst:AppDatabase) {
    fun InsertTask_repo(newTask:Tasks_Info_Class){
        DaoInst
            .TasksDaoInst()
            .InsertTasks_dao(newTask)
    }
    fun modifyTask_repo(oldTask:String,NewTask:String){
        DaoInst.TasksDaoInst().ModifyTasks_dao(oldTask,NewTask)

    }
    fun modifyDate_repo(oldDate:String,NewDate:String){
        DaoInst.TasksDaoInst().ModifyDate_dao(oldDate,NewDate)

    }
    fun DeleteTask_repo(oldTask:Tasks_Info_Class){
        DaoInst.TasksDaoInst().deleteTasks_dao(oldTask)

    }
    suspend fun GetTask_repo():List<Tasks_Info_Class>{
        return DaoInst.TasksDaoInst().GetAllTasks_dao()
    }

}