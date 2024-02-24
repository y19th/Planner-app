package com.example.domain.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.datastore.SettingsDataStore
import com.example.data.datastore.SettingsDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainDataStoreModule {

    @Provides
    fun provideSettingsDataStore(dataStore: DataStore<Preferences>) : SettingsDataStore {
        return SettingsDataStoreImpl(dataStore)
    }
}