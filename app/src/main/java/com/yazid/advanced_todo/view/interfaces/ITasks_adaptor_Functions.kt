package com.yazid.advanced_todo.view.interfaces

import com.yazid.advanced_todo.model.Tasks_Info_Class

interface ITasks_adaptor_Functions {
    fun Delete_Item_in_Adaptor(Task:Tasks_Info_Class):Unit
    fun Go_T_modify_Item_in_Adaptor(Task:Tasks_Info_Class):Unit

    fun SetTaskState_Adaptor(Task:Tasks_Info_Class,State:String):Unit
}