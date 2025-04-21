package com.example.todolist.repository

import com.example.todolist.domain.Task

interface ToDoRepository {
    suspend fun getAllData(): List<Task>

    suspend fun insertTasks(vararg tasks: Task)

    suspend fun deleteTasks(vararg tasks: Task)
}