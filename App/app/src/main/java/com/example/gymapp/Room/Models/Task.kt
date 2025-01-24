package com.example.gymapp.Room.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "priority")
    val priority: Int,
    @ColumnInfo(name = "finished")
    val finished: Boolean
){}
