package com.example.gymapp.Room.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymapp.Room.Aplication.TasksAplication
import com.example.gymapp.Room.Entities.Task
import com.example.gymapp.Room.Repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    fun getAll(): Flow<List<Task>> = tasksRepository.getAll()

    fun insertTask(description: String, priority: Int) = viewModelScope.launch {
        tasksRepository.insertTask(Task(description = description, priority = priority))
    }

    fun deleteAllTasks(allTasks: List<Task>) = viewModelScope.launch {
        tasksRepository.deleteAllTasks(allTasks)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TasksAplication)
                TasksViewModel(application.container.tasksRepository)
            }
        }
    }
}