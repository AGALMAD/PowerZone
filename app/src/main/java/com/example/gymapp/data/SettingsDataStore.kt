package com.example.gymapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private val dataStoreName = "gym_app_configuration";

class SettingsDataStore {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("musclesPerWeek")
        val USER_EMAIL_KEY = stringPreferencesKey("musclesPerWeek")
    }

}