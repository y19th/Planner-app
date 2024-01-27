package com.example.planner_app.data.repository

import com.example.planner_app.domain.models.TaskStatus
import com.example.planner_app.room.dao.MainDao
import com.example.planner_app.room.entities.TaskEntity
import com.example.planner_app.room.entities.toListTaskModel
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val taskDao: MainDao
) {
    fun receiveTasks() = taskDao.getTasks().toListTaskModel()

    fun addTask(vararg taskEntity: TaskEntity) {
        taskEntity.forEach {
            taskDao.insertTask(it)
        }
    }

    fun updateTask(
        taskId: Int,
        onUpdate: () -> Unit
    ) {
        taskDao.findTaskById(taskId)?.let { entity ->
            taskDao.updateTaskEntity(entity.copy(status = TaskStatus.COMPLETED))
            onUpdate.invoke()
        }
    }
}