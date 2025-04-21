package com.example.todolist.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {
    @Query(
        """
            SELECT *
            FROM TaskEntity
        """
    )
    suspend fun searchTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(
        vararg tasks: TaskEntity
    )

    @Delete
    suspend fun deleteTasks(
        vararg tasks: TaskEntity
    )
}