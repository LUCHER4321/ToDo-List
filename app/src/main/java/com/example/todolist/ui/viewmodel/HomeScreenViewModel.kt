package com.example.todolist.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolist.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    var dataVisualizerState by mutableStateOf(DataVisualizerState())
        private set

    private fun editTasks(edit: (MutableList<Task>) -> Unit){
        val prevTasks = dataVisualizerState.myTasks.toMutableList()
        edit(prevTasks)
        dataVisualizerState = dataVisualizerState.copy(myTasks = prevTasks)
    }

    private fun editTask(task: Task, copy: (Task) -> Task){
        val newTask = copy(task)
        editTasks { pt ->
            pt.replaceAll { if(it.id == task.id) newTask else it }
        }
    }

    fun addTask(name: String){
        editTasks { pt ->
            val id = if(pt.isEmpty()) 0 else pt.maxOf { it.id } + 1
            pt.add(Task(id, name))
        }
    }

    fun removeTask(task: Task){
        editTasks { pt ->
            pt.remove(task)
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