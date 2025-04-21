package com.example.todolist.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val completed: Boolean = false,
)
