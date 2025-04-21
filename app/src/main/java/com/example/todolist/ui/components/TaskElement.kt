package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Row
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
    Row(modifier = modifier) {
        Text(text = task.name)
        Button(
            modifier = Modifier.weight(1f),
            onClick = { viewModel.setEditTask(task) },
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit Task"
            )
        }
        Checkbox(
            checked = task.completed,
            onCheckedChange = { viewModel.switchTask(task) },
        )
        Button(
            modifier = Modifier.weight(1f),
            onClick = { viewModel.removeTask(task) },
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Remove Task"
            )
        }
    }
}