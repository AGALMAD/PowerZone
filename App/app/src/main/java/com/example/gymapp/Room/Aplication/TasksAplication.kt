package com.example.gymapp.Room.Aplication

import android.app.Application
import com.example.gymapp.Room.Containers.TasksContainer

class TasksAplication : Application() {

    lateinit var container: TasksContainer

    override fun onCreate() {
        super.onCreate()
        container = TasksContainer(this)
    }
}