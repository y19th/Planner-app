package com.example.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.util.Handler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): SettingsDataStore {
    private val coreContext = Handler.context(dispatcher)

    private val themeKey = intPreferencesKey("settings_theme_key")

    override suspend fun writeTheme(value: Int): Unit = withContext(coreContext) {
        dataStore.edit { setting ->
            setting[themeKey] = value
        }
    }

    override suspend fun readTheme(): Int {
        val res = dataStore.data.map { it[themeKey] ?: 0 }
        return res.first()
    }

    override suspend fun collectTheme(): Flow<Int> = withContext(coreContext) {
        return@withContext dataStore.data.map { it[themeKey] ?: 0 }
    }

}

interface SettingsDataStore {

    suspend fun writeTheme(value: Int)

    suspend fun readTheme(): Int

    suspend fun collectTheme(): Flow<Int>
}

