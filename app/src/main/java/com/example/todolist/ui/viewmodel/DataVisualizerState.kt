package com.example.todolist.ui.viewmodel

import com.example.todolist.domain.Task

data class DataVisualizerState(
    val myTasks: List<Task> = emptyList(),
    val mode: ViewMode = ViewMode.TASKS,
    val editingTask: Task? = null,
    val newName: String = "",
    val showCompleted: Boolean = false,
)
