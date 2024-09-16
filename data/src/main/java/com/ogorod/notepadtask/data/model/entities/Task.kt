package com.ogorod.notepadtask.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val text: String,
    val favorite: Boolean,
    val date: String,
    val delete: Boolean = false,
    val timerDelete: Long? = null
)
