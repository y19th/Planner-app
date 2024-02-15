package com.example.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.models.PinList
import com.example.data.models.TaskStatusModel
import com.example.data.models.TaskTimeModel

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String = "title",
    @ColumnInfo(name = "content") val content: String = "content",
    @ColumnInfo(name = "date_from") val dateFrom: TaskTimeModel = TaskTimeModel(),
    @ColumnInfo(name = "date_to") val dateTo: TaskTimeModel = TaskTimeModel(),
    @ColumnInfo(name = "status") val status: TaskStatusModel = TaskStatusModel.IN_PROGRESS,
    @ColumnInfo(name = "pins") val taskPin: PinList = PinList()
)