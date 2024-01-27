package com.example.planner_app.domain.usecase

import com.example.planner_app.data.repository.RoomRepository
import com.example.planner_app.room.entities.TaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun receiveTasks() = withContext(defaultDispatcher) {
        repository.receiveTasks()
    }

    suspend fun addTask(vararg taskEntity: TaskEntity) = withContext(defaultDispatcher) {
        taskEntity.forEach { taskEntity -> repository.addTask(taskEntity) }
    }

    suspend fun updateTask(
        taskId: Int,
        onUpdate: () -> Unit
    ) = withContext(defaultDispatcher) {
        repository.updateTask(
            taskId = taskId,
            onUpdate = onUpdate
        )
    }
}