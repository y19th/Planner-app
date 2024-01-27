package com.example.planner_app.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planner_app.domain.models.TaskModel
import com.example.planner_app.domain.models.TaskPin
import com.example.planner_app.domain.models.TaskStatus
import com.example.planner_app.room.converters.PinList

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int ,
    @ColumnInfo(name = "title") val title: String = "title",
    @ColumnInfo(name = "content") val content: String = "content",
    @ColumnInfo(name = "date_from") val dateFrom: String = "from date",
    @ColumnInfo(name = "date_to") val dateTo: String = "to date",
    @ColumnInfo(name = "status") val status: TaskStatus = TaskStatus.IN_PROGRESS,
    @ColumnInfo(name = "pins") val taskPin: PinList = PinList()
) {
    fun toTaskModel() = TaskModel(
        id, title, content, dateFrom, dateTo, status, taskPin.list
    )
}

fun List<TaskEntity>.toListTaskModel(): List<TaskModel> {
    return this.map { entity -> entity.toTaskModel() }
}