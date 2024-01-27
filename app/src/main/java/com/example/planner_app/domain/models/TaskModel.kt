package com.example.planner_app.domain.models

import androidx.compose.runtime.Immutable

/*TODO maybe make from and to with date*/
/*maybe this should be immutable*/

@Immutable
data class TaskModel(
    val id: Int = -1,
    val title: String = "title",
    val content: String = "content",
    val dateFrom: String = "from date",
    val dateTo: String = "to date",
    val status: TaskStatus = TaskStatus.IN_PROGRESS,
    val taskPin: List<TaskPin> = listOf()
)
