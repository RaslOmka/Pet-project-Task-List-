package com.ogorod.notepadtask.data.mapper

import com.ogorod.notepadtask.data.model.entities.Task
import com.ogorod.notepadtask.domain.dto.TaskDTO

class TaskEntityMapper {

    fun toDTO(task: Task): TaskDTO =
        TaskDTO(
            id = task.id,
            title = task.title,
            text = task.text,
            favorite = task.favorite,
            dateCreate = task.date,
            delete = task.delete,
            timerDelete = task.timerDelete
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