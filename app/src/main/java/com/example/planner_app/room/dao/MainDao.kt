package com.example.planner_app.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.planner_app.room.entities.TaskEntity

@Dao
interface MainDao {

    @Query("select * from task")
    fun getTasks() : List<TaskEntity>

    @Insert
    fun insertTask(vararg tasks: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Update
    fun updateTaskEntity(taskEntity: TaskEntity)

    @Query("select * from task where id = :entityId")
    fun findTaskById(entityId: Int) : TaskEntity?

}