package com.example.gymapp.Room.Containers

import android.content.Context
import com.example.gymapp.Room.Databases.TasksDatabase
import com.example.gymapp.Room.Repositories.TasksRepository

class TasksContainer(private val context: Context) {
    val tasksRepository: TasksRepository by lazy {
        TasksRepository(TasksDatabase.getTasksDatabase(context).TasksDao())
    }
}