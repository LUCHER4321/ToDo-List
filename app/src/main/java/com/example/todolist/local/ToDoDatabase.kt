package com.example.todolist.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        TaskEntity::class,
    ]
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract val dao: ToDoDao
}