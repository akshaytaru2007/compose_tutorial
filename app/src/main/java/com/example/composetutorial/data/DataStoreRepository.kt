package com.example.composetutorial.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "compose_tutorial_preference")

class DataStoreRepository(context: Context) {

    private object PreferenceKeys {
        val loginKey = booleanPreferencesKey("LOGIN_KEY")
    }

    private val _dataStore = context.dataStore

    suspend fun saveLoginState(isLoggedIn: Boolean) {
        _dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.loginKey] = isLoggedIn
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return _dataStore.data.catch { cause ->
            if (cause is IOException) {
                emit(emptyPreferences())
            }
            else {
                throw cause
            }
        }.map { preferences ->
           preferences[PreferenceKeys.loginKey] ?: false
        }
    }
}