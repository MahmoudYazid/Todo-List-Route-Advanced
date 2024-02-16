package com.yazid.advanced_todo.repo.offline_resources

import com.yazid.advanced_todo.model.Tasks_Info_Class
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


public class Room_Functions_Implementation @Inject constructor( public  val DaoInst:AppDatabase) {
    suspend fun InsertTask_repo(newTask:Tasks_Info_Class){
        DaoInst
            .TasksDaoInst()
            .InsertTasks_dao(newTask)
    }
    suspend fun modifyTask_repo(oldTask_Repo:String,NewTask_Repo:String,Olddate_Repo:String,NewDate_Repo:String){
        DaoInst.TasksDaoInst().ModifyTasks_dao(
            oldDate = Olddate_Repo,
            newTask = NewTask_Repo,
            oldTask = oldTask_Repo,
            newDate = NewDate_Repo


        )

    }
    suspend fun DeleteTask_repo(oldTask:Tasks_Info_Class){
        DaoInst.TasksDaoInst().deleteTasks_dao(oldTask)

    }
    suspend fun GetTask_repo():List<Tasks_Info_Class>{
        return DaoInst.TasksDaoInst().GetAllTasks_dao()
    }
    suspend fun GetAllTasksInThisDay_repo(InputDate:String):List<Tasks_Info_Class>{
        return DaoInst.TasksDaoInst().GetAllTasksInThisDay_dao(InputDate)
    }
}