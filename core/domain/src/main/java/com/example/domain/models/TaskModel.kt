package com.example.domain.models

import androidx.compose.runtime.Immutable
import com.example.data.room.entities.TaskEntity

/*TODO maybe make from and to with date*/
/*maybe this should be immutable*/

@Immutable
data class TaskModel(
    val id: Int = 0,
    val title: String = "title",
    val content: String = "content",
    val dateFrom: String = "from date",
    val dateTo: String = "to date",
    val status: TaskStatus = TaskStatus.IN_PROGRESS,
    val taskPin: List<TaskPin> = listOf()
) {
    fun toTaskEntity() = TaskEntity(
        id, title, content, dateFrom, dateTo
    )
}

fun TaskEntity.toTaskModel() = TaskModel(
    id, title, content, dateFrom, dateTo, status.toTaskStatus()
)
