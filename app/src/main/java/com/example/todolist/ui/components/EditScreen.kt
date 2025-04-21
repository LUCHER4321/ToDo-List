package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.todolist.R
import com.example.todolist.ui.viewmodel.HomeScreenViewModel

@Composable
fun EditScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
){
    Column(modifier = modifier) {
        TextField(
            value = viewModel.dataVisualizerState.newName,
            onValueChange = { viewModel.setNewName(it) }
        )
        Row {
            Button(onClick = {
                if(viewModel.dataVisualizerState.editingTask == null){
                    viewModel.addTask(viewModel.dataVisualizerState.newName)
                } else{
                    viewModel.renameTask(viewModel.dataVisualizerState.editingTask!!, viewModel.dataVisualizerState.newName)
                }
                viewModel.setEditTask()
            }) {
                Text(text = stringResource(if (viewModel.dataVisualizerState.editingTask == null) R.string.add_task else R.string.edit_task))
            }
            Button(onClick = { viewModel.setEditTask() }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    }
}