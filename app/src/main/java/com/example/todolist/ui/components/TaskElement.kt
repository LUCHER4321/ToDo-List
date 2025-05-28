package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todolist.domain.Task
import com.example.todolist.ui.viewmodel.HomeScreenViewModel

@Composable
fun TaskElement(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    task: Task,
){
    LazyRow (modifier = modifier) {
        item {
            Text(text = task.name)
        }
        item {
            Button(
                modifier = Modifier,
                onClick = { viewModel.setEditTask(task) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Task"
                )
            }
        }
        item {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { viewModel.switchTask(task) },
            )
        }
        item {
            Button(
                modifier = Modifier,
                onClick = { viewModel.removeTask(task) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove Task"
                )
            }
        }
    }
}