package com.yazid.advanced_todo.repo.offline_resources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yazid.advanced_todo.model.Tasks_Info_Class
import javax.inject.Singleton

@Database(entities = [Tasks_Info_Class::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun TasksDaoInst(): Dao
}