package com.example.planner_app.room.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.planner_app.room.converters.Converters
import com.example.planner_app.room.dao.MainDao
import com.example.planner_app.room.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainSchema: RoomDatabase() {
    abstract fun MainDao() : MainDao

    companion object {
        @Volatile
        private var INSTANCE: MainSchema? = null

        fun receiveInstance(context: Context): MainSchema {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainSchema::class.java,
                    "main_schema"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}