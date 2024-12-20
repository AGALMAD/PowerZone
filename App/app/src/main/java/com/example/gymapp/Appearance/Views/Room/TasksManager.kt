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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext


@Composable
fun TasksManager(navController: NavHostController,
                 viewModel: TasksViewModel = viewModel(factory = TasksViewModel.Factory) // ..1
) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val taskList by viewModel.getAll().collectAsState(initial = emptyList()) // ..2
    var taskDescription by remember { mutableStateOf("") }
    var taskPriority by remember { mutableIntStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {

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
                        when (task.priority) {
                            1 -> Text(text = "Baja", style = MaterialTheme.typography.displaySmall)
                            2 -> Text(text = "Media", style = MaterialTheme.typography.displaySmall)
                            3 -> Text(text = "Alta", style = MaterialTheme.typography.displaySmall)
                        }

                        Text(text = task.description, style = MaterialTheme.typography.displaySmall)



                    }
                }
            }
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.3F)
        ) {
            // Campo de texto para la descripción
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text("Descripción de la tarea") }
            )

            // Dropdown para la prioridad
            GenerateDropDown(taskPriority) { priority ->
                taskPriority = priority // Actualiza el entero seleccionado
            }

            Row {
                Button(onClick = { viewModel.insertTask(taskDescription, taskPriority) }) {
                    Text(text = "SAVE")
                }
                Button(onClick = { viewModel.deleteAllTasks(taskList) }) {
                    Text(text = "ALL DELETE")
                }
            }
        }

    }


}



@Composable
fun GenerateDropDown(selectedPriority: Int, onPrioritySelected: (Int) -> Unit) {
    val opciones = listOf(
        "Alta" to 1,
        "Media" to 2,
        "Baja" to 3)

    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedButton(onClick = { expanded = true }) {
            Text(text = opciones.firstOrNull { it.second == selectedPriority }?.first ?: "Selecciona prioridad")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            //Recorre la lista creando items con sus valores
            opciones.forEach() {(text, value) ->
                DropdownMenuItem(
                    text = { Text(text) },
                    onClick = {
                        onPrioritySelected(value) // Pasa el valor entero seleccionado
                        expanded = false
                    }
                )
            }
        }
    }
}



