package com.example.domain.usecase

import com.example.data.repository.RoomRepository
import com.example.domain.models.TaskModel
import com.example.domain.models.toTaskModel
import com.example.util.Handler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val coreContext = defaultDispatcher + Handler.coroutineExceptionHandler


    suspend fun receiveTasks() = withContext(coreContext) {
        repository.receiveTasks().map { entity -> entity.toTaskModel() }
    }

    suspend fun addTask(vararg taskEntity: TaskModel) = withContext(coreContext) {
        taskEntity.forEach { taskEntity -> repository.addTask(taskEntity.toTaskEntity()) }
    }

    suspend fun updateTask(
        taskId: Int,
        onUpdate: () -> Unit
    ) = withContext(coreContext) {
        repository.updateTask(
            taskId = taskId,
            onUpdate = onUpdate
        )
    }
}