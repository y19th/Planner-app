package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Singleton

@Singleton
object DataStore {

    private const val storeName = "settings"

    val Context.instance: DataStore<Preferences> by preferencesDataStore(name = storeName)
}