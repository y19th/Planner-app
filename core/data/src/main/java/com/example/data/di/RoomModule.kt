package com.example.data.di

import android.content.Context
import com.example.data.repository.RoomRepository
import com.example.data.room.dao.MainDao
import com.example.data.room.schema.MainSchema
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideMainScheme(@ApplicationContext context: Context): MainSchema {
        return MainSchema.receiveInstance(context = context)
    }

    @Provides
    fun provideMainDao(mainSchema: MainSchema): MainDao {
        return mainSchema.MainDao()
    }

    @Provides
    fun provideRoomRepository(mainDao: MainDao): RoomRepository {
        return RoomRepository(taskDao = mainDao)
    }
}

