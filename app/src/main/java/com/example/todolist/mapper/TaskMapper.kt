package com.example.todolist.mapper

import com.example.todolist.domain.Task
import com.example.todolist.local.TaskEntity

fun TaskEntity.toTask(): Task{
    return Task(
        id = this.id,
        name = this.name,
        completed = this.completed
    )
}

fun Task.toTaskEntity(): TaskEntity{
    return TaskEntity(
        id = this.id,
        name = this.name,
        completed = this.completed
    )
}