package com.example.gymapp.GymApi.Data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gymapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val dataStoreName = "gym_app_authentication";


class AuthDataStore(private val context: Context) {


    companion object {
        private val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(
            dataStoreName
        )

        //Datos del usuario
        val userName = stringPreferencesKey("userName")
        val email = stringPreferencesKey("email")
        val accessToken = stringPreferencesKey("accessToken")
        val refreshToken = stringPreferencesKey("refreshToken")


    }


    /*********** Funciones para obtener y cambiar los datos *************/

    val getUserName: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            preferences[userName] ?: context.getString(R.string.name_label)
        }

    suspend fun setSwitchCardioTime(newUserName : String) {
        context.authDataStore.edit { preferences ->
            preferences[userName] = newUserName
        }
    }

    val getEmail: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            preferences[email] ?: context.getString(R.string.emailWord)
        }

    suspend fun setEmail(newEmail : String) {
        context.authDataStore.edit { preferences ->
            preferences[email] = newEmail
        }
    }

    val getAccessToken: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            preferences[accessToken]
        }

    suspend fun setAccessToken(newAccessToken : String) {
        context.authDataStore.edit { preferences ->
            preferences[accessToken] = newAccessToken
        }
    }

    val getRefreshToken: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            preferences[refreshToken]
        }

    suspend fun setRefreshToken(newRefreshToken : String) {
        context.authDataStore.edit { preferences ->
            preferences[refreshToken] = newRefreshToken
        }
    }

}