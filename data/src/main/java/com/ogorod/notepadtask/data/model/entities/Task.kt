package com.ogorod.notepadtask.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ogorod.notepadtask.domain.dto.TaskDTO

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
) {

    companion object {
        val taskEmpty = Task(
            title = "",
            text = "",
            favorite = false,
            date = "",
            delete = false,
            timerDelete = null
        )
    }

    fun toDTO(): TaskDTO =
        TaskDTO(
            id = id,
            title = title,
            text = text,
            favorite = favorite,
            dateCreate = date,
            delete = delete,
            timerDelete = timerDelete
        )

    fun fromDTO(taskDTO: TaskDTO): Task = Task(
        id = taskDTO.id,
        title = taskDTO.title,
        text = taskDTO.text,
        favorite = taskDTO.favorite,
        date = taskDTO.dateCreate,
        delete = taskDTO.delete,
        timerDelete = taskDTO.timerDelete
    )
}
