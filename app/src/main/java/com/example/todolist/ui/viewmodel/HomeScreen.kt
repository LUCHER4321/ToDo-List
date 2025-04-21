package com.example.todolist.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.ui.components.EditScreen
import com.example.todolist.ui.components.TasksScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
){
    when(viewModel.dataVisualizerState.mode){
        ViewMode.TASKS -> TasksScreen(
            modifier = modifier,
            viewModel = viewModel
        )
        ViewMode.EDIT -> EditScreen(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}