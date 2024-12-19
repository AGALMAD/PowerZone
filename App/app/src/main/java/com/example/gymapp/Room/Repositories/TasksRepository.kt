package com.example.gymapp.Room.Repositories

import com.example.gymapp.Room.Entities.Task
import com.example.gymapp.Room.Daos.TasksDao

class TasksRepository(private val tasksDao: TasksDao) {

    fun getAll() = tasksDao.getAll()

    suspend fun insertTask(task: Task)
            = tasksDao.insertTask(task)

    suspend fun deleteAllTasks(allTasks : List<Task>)
            = tasksDao.deleteTask(allTasks)
}