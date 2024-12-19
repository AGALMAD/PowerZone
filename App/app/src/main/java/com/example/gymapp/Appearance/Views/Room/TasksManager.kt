package com.example.gymapp.Appearance.Views.Room

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gymapp.Room.ViewModels.TasksViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items


@Composable
fun TasksManager(navController: NavHostController,
                 viewModel: TasksViewModel = viewModel(factory = TasksViewModel.Factory) // ..1
) {

    val scrollState = rememberScrollState()

    val taskList by viewModel.getAll().collectAsState(initial = emptyList()) // ..2
    var taskDescription by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Activar scroll vertical
            .background(MaterialTheme.colorScheme.background), //!!!!!! Importante para que cambie el color de fondo
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        // Display the list of friends
        LazyColumn(
            modifier = Modifier.weight(.7F),
            verticalArrangement = Arrangement.Center
        ) {
            items(taskList) { task ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = task.description, style = MaterialTheme.typography.displaySmall)
                        Text(text = task.priority, style = MaterialTheme.typography.displaySmall)

                    }
                }
            }
        }

        // Input field and buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(.3F)
        ) {
            OutlinedTextField(value = taskDescription, onValueChange = { taskDescription = it })
            OutlinedTextField(value = taskPriority, onValueChange = { taskPriority = it })
            Button(onClick = { viewModel.insertTask(taskDescription,taskPriority) }) {
                Text(text = "SAVE")
            }
            Button(onClick = { viewModel.deleteAllTasks(taskList) }) {
                Text(text = "ALL DELETE")
            }
        }
    }


}
