package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.data.datastore.DataStore.instance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ) : DataStore<Preferences> {
        return context.instance
    }

}