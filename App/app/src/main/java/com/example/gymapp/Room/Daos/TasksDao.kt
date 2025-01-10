package com.example.gymapp.Room.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gymapp.Room.Models.Task
import kotlinx.coroutines.flow.Flow

//Interfaz para manejar los datos de la base de datos
@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(allMyFriends: List<Task>)

}