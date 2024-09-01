package com.ogorod.notepadtask.domain.dto

data class TaskDTO(
    val id: Int?,
    val title: String,
    val text: String,
    val favorite: Boolean = false,
    val dateCreate: String,
    val delete: Boolean = false,
    val timerDelete: Long? = null
)
