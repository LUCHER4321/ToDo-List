package com.example.todolist.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolist.domain.Task
import com.example.todolist.repository.ToDoRepository
import com.example.todolist.util.coroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: ToDoRepository,
) : ViewModel() {
    var dataVisualizerState by mutableStateOf(DataVisualizerState())
        private set

    init {
        loadTasks()
    }

    private fun loadTasks(){
        coroutine {
            val allTasks = repository.getAllData()
            println("Local Tasks: $allTasks")
            kotlinx.coroutines.withContext(Dispatchers.Main){
                dataVisualizerState = dataVisualizerState.copy(myTasks = allTasks)
            }
        }
    }

    private fun editTasks(edit: (MutableList<Task>) -> Unit){
        val prevTasks = dataVisualizerState.myTasks.toMutableList()
        edit(prevTasks)
        dataVisualizerState = dataVisualizerState.copy(myTasks = prevTasks)
    }

    private fun editTask(task: Task, copy: (Task) -> Task){
        val newTask = copy(task)
        insertTask(newTask)
        editTasks { pt ->
            pt.replaceAll { if(it.id == task.id) newTask else it }
        }
    }

    private fun insertTask(task: Task){
        coroutine {
            repository.insertTasks(task)
        }
    }

    fun addTask(name: String){
        editTasks { pt ->
            val id = if(pt.isEmpty()) 0 else pt.maxOf { it.id } + 1
            val task = Task(id, name)
            insertTask(task)
            pt.add(task)
        }
    }

    fun removeTask(task: Task){
        editTasks { pt ->
            pt.remove(task)
        }
        coroutine {
            repository.deleteTasks(task)
        }
    }

    fun switchTask(task: Task){
        editTask(task) { it.copy(completed = !task.completed) }
    }

    fun renameTask(task: Task, name: String){
        editTask(task) { it.copy(name = name) }
    }

    private fun changeMode(){
        val newMode = when(dataVisualizerState.mode){
            ViewMode.TASKS -> ViewMode.EDIT
            ViewMode.EDIT -> ViewMode.TASKS
        }
        dataVisualizerState = dataVisualizerState.copy(mode = newMode)
    }

    fun setEditTask(task: Task? = null){
        dataVisualizerState = dataVisualizerState.copy(
            editingTask = task,
            newName = task?.name ?: "",
        )
        changeMode()
    }

    fun setNewName(name: String){
        dataVisualizerState = dataVisualizerState.copy(newName = name)
    }

    fun switchShowCompleted(){
        dataVisualizerState = dataVisualizerState.copy(showCompleted = !dataVisualizerState.showCompleted)
    }
}