package com.example.gymapp.Room

import android.content.Context

class TasksContainer(private val context: Context) {
    val tasksRepository: TasksRepository by lazy {
        TasksRepository(TasksDatabase.getTasksDatabase(context).TasksDao())
    }
}