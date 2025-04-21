package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todolist.R
import com.example.todolist.domain.Task
import com.example.todolist.ui.viewmodel.HomeScreenViewModel

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
){
    val sortedTasks = viewModel.dataVisualizerState.myTasks.sortedBy { -it.id }
    val uncompletedTasks = sortedTasks.filter { !it.completed }
    //val completedTasks = sortedTasks.filter { it.completed }
    Column(modifier = modifier) {
        LazyRow {
            item {
                Button(onClick = { viewModel.setEditTask() }) {
                    Text(text = stringResource(R.string.add_task))
                }
            }
            item {
                Text(text = stringResource(R.string.show_completed))
            }
            item {
                Checkbox(
                    checked = viewModel.dataVisualizerState.showCompleted,
                    onCheckedChange = { viewModel.switchShowCompleted() }
                )
            }
        }
        Row (modifier = Modifier.fillMaxWidth()) {
            listOf(if(viewModel.dataVisualizerState.showCompleted) sortedTasks else uncompletedTasks).forEach {
                TaskList(
                    viewModel = viewModel,
                    tasks = it
                )
            }
        }
    }
}

@Composable
private fun TaskList(
    viewModel: HomeScreenViewModel,
    tasks: List<Task>,
){
    LazyColumn {
        items(
            items = tasks,
            key = { it.id }
        ){
            TaskElement(
                viewModel = viewModel,
                task = it
            )
        }
    }
}