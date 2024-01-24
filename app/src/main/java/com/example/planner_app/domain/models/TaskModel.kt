package com.example.planner_app.domain.models

/*TODO maybe make from and to with date*/
/*maybe this should be immutable*/
data class TaskModel(
    val title: String = "title",
    val content: String = "content",
    val dateFrom: String = "from date",
    val dateTo: String = "to date",
    val status: TaskStatus = TaskStatus.IN_PROGRESS,
    val taskPin: List<TaskPin> = listOf()
)
