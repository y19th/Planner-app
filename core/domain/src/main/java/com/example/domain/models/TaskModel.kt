package com.example.domain.models

import androidx.compose.runtime.Immutable
import com.example.data.models.PinList
import com.example.data.room.entities.TaskEntity
import com.example.domain.states.TaskTime
import com.example.domain.states.toTaskTime
import com.example.domain.states.toTaskTimeModel

/*TODO maybe make from and to with date*/

@Immutable
data class TaskModel(
    val id: Int = 0,
    val title: String = "title",
    val content: String = "content",
    val dateFrom: TaskTime = TaskTime(),
    val dateTo: TaskTime = TaskTime(),
    val status: TaskStatus = TaskStatus.IN_PROGRESS,
    val taskPin: List<TaskPin> = listOf()
) {
    fun toTaskEntity() = TaskEntity(
        id = id,
        title = title,
        content = content,
        dateFrom = dateFrom.toTaskTimeModel(),
        dateTo = dateTo.toTaskTimeModel(),
        status = status.toTaskStatusModel(),
        taskPin = PinList(taskPin.toListTaskPinModel())
    )
}

fun TaskEntity.toTaskModel() = TaskModel(
    id,
    title,
    content,
    dateFrom.toTaskTime(),
    dateTo.toTaskTime(),
    status.toTaskStatus(),
    taskPin.list.toListTaskPin()
)
