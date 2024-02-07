package com.example.domain.di

import com.example.data.repository.RoomRepository
import com.example.domain.usecase.RoomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainRoomModule {

    @Provides
    fun provideRoomUseCase(roomRepository: RoomRepository): RoomUseCase {
        return RoomUseCase(roomRepository)
    }
}