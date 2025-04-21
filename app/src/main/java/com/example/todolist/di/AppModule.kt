package com.example.todolist.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.ToDoList
import com.example.todolist.local.ToDoDatabase
import com.example.todolist.repository.ToDoRepository
import com.example.todolist.repository.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ToDoDatabase{
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "ToDo.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoList(): ToDoList{
        return ToDoList()
    }

    @Provides
    @Singleton
    fun provideRepository(db: ToDoDatabase): ToDoRepository{
        return ToDoRepositoryImpl(db)
    }
}