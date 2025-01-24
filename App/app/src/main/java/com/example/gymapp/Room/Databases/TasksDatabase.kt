package com.example.gymapp.Room.Databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymapp.Room.Models.Task
import com.example.gymapp.Room.Daos.TasksDao

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase : RoomDatabase(){

    abstract fun TasksDao(): TasksDao


    companion object {
        @Volatile
        private var Instance: TasksDatabase? = null

        fun getTasksDatabase(context: Context): TasksDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = TasksDatabase::class.java,
                    name = "tasks2"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }

}