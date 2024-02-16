package com.yazid.advanced_todo.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazid.advanced_todo.model.Tasks_Info_Class
import com.yazid.advanced_todo.repo.offline_resources.Room_Functions_Implementation
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
public class ViewModelGeneral @Inject public constructor(public val repoInst:Room_Functions_Implementation):ViewModel() {

    val LiveDataTasks: MutableLiveData<List<Tasks_Info_Class>> = MutableLiveData()
     suspend fun GetData_ViewModel(){
            val data = repoInst.GetTask_repo()
            withContext(Dispatchers.Main) {
                LiveDataTasks.value=data
            }
    }

    suspend fun GetAllTasksInThisDay_ViewModel(InputDate:String){
        val data = repoInst.GetAllTasksInThisDay_repo(InputDate)
        withContext(Dispatchers.Main) {
            LiveDataTasks.value=data
        }
    }
     suspend fun Insert_vm(Task:Tasks_Info_Class){
                repoInst.InsertTask_repo(Task)
                GetData_ViewModel()
    }
    suspend fun Delete_vm(Task:Tasks_Info_Class){
            repoInst.DeleteTask_repo(Task)
            GetData_ViewModel()
    }



    suspend fun modifyTask_vm(oldTask_: String,
                              NewTask_: String,
                              NewDate_:String,
                              Olddate_: String){
        repoInst.modifyTask_repo(
            oldTask_Repo = oldTask_,
            NewTask_Repo = NewTask_,
            NewDate_Repo = NewDate_,
            Olddate_Repo= Olddate_)
        GetData_ViewModel()
    }
}