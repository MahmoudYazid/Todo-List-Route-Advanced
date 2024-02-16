package com.yazid.advanced_todo.dependacy_inj

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yazid.advanced_todo.repo.offline_resources.AppDatabase
import com.yazid.advanced_todo.repo.offline_resources.Dao
import com.yazid.advanced_todo.repo.offline_resources.Room_Functions_Implementation
import com.yazid.advanced_todo.view_model.ViewModelGeneral
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object module {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "tasksDb"
        )
            .fallbackToDestructiveMigration()

            .build()
    }



    @Provides
    @Singleton
    fun provide_DatabaseRepo_Inst(Dao:AppDatabase):Room_Functions_Implementation{
        return  Room_Functions_Implementation(Dao)
    }


}