package com.example.todolist.repository

import com.example.todolist.domain.Task
import com.example.todolist.local.ToDoDatabase
import com.example.todolist.mapper.toTask
import com.example.todolist.mapper.toTaskEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoRepositoryImpl @Inject constructor(
    private val db: ToDoDatabase
): ToDoRepository {
    private val dao = db.dao

    override suspend fun getAllData(): List<Task> {
        return dao.searchTasks().map { it.toTask() }
    }

    override suspend fun insertTasks(vararg tasks: Task) {
        dao.insertTasks(*tasks.map { it.toTaskEntity() }.toTypedArray())
    }

    override suspend fun deleteTasks(vararg tasks: Task) {
        dao.deleteTasks(*tasks.map { it.toTaskEntity() }.toTypedArray())
    }
}