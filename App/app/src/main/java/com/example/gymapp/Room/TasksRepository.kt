package com.example.gymapp.Room

class TasksRepository(private val tasksDao: TasksDao) {

    fun getAll() = tasksDao.getAll()

    suspend fun insertTask(task: Task)
            = tasksDao.insertTask(task)

    suspend fun deleteAllTasks(allTasks : List<Task>)
            = tasksDao.deleteTask(allTasks)
}