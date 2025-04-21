package com.example.todolist.domain

data class Task(
    val id: Int,
    val name: String,
    val completed: Boolean = false,
)
