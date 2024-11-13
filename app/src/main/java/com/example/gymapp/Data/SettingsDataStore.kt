package com.example.gymapp.Data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val dataStoreName = "gym_app_configuration";

class SettingsDataStore(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStoreSettings: DataStore<Preferences> by preferencesDataStore( dataStoreName)

        //Radio Buttons
        val radioButtonPartOfBody = intPreferencesKey("radioButtonPartOfBody")


        //Tren superior
        val checkBoxPecho = booleanPreferencesKey("checkBoxPecho")
        val checkBoxEspalda = booleanPreferencesKey("checkBoxEspalda")
        val checkBoxHombro = booleanPreferencesKey("checkBoxHombro")
        val checkBoxBiceps = booleanPreferencesKey("checkBoxBiceps")
        val checkBoxTriceps = booleanPreferencesKey("checkBoxTriceps")

        //Tren Inferior
        val checkBoxCuadriceps = booleanPreferencesKey("checkBoxCuadriceps")
        val checkBoxGemelos = booleanPreferencesKey("checkBoxGemelos")
        val checkBoxFemoral = booleanPreferencesKey("checkBoxFemoral")
        val checkBoxGluteo = booleanPreferencesKey("checkBoxGluteo")

        //Cardio
        val switchCardio = booleanPreferencesKey("switchCardio")
        //Tiempo de cardio que quiere hacer el usuario (minutos)
        val cardioTime = intPreferencesKey("cardioTime")

    }


    /*********** Funciones para obtener y cambiar los datos *************/

    //Radio Button de la parte del cuerpo que quiere entrenar
    val getRadioButtonPartOfBody: Flow<Int?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[radioButtonPartOfBody] ?: 1
        }

    suspend fun setRadioButtonPartOfBody(partOfBodySelected : Int) {
        context.dataStoreSettings.edit { preferences ->
            preferences[radioButtonPartOfBody] = partOfBodySelected
        }
    }

    //Check box de pecho
    val getCheckBoxPecho: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxPecho] ?: false
        }

    suspend fun setCheckBoxPecho(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxPecho] = selected
        }
    }

    //Check box de espalda
    val getCheckBoxEspalda: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxEspalda] ?: false
        }

    suspend fun setCheckBoxEspalda(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxEspalda] = selected
        }
    }

    //Check box de hombro
    val getCheckBoxHombro: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxHombro] ?: false
        }

    suspend fun setCheckBoxHombro(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxHombro] = selected
        }
    }

    //Check box de biceps
    val getCheckBoxBiceps: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxBiceps] ?: false
        }

    suspend fun setCheckBoxBiceps(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxBiceps] = selected
        }
    }

    //Check box de triceps
    val getCheckBoxTriceps: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxTriceps] ?: false
        }

    suspend fun setCheckBoxTriceps(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxTriceps] = selected
        }
    }

    //Check box de cuadriceps
    val getCheckBoxCuadriceps: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxCuadriceps] ?: false
        }

    suspend fun setCheckBoxCuadriceps(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxCuadriceps] = selected
        }
    }

    //Check box de Femoral
    val getCheckBoxFemoral: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxFemoral] ?: false
        }

    suspend fun setCheckBoxFemoral(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxFemoral] = selected
        }
    }

    //Check box de Gemelos
    val getCheckBoxGemelos: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxGemelos] ?: false
        }

    suspend fun setCheckBoxGemelos(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxGemelos] = selected
        }
    }
    //Check box de Gluteo
    val getCheckBoxGluteo: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[checkBoxGluteo] ?: false
        }

    suspend fun setCheckBoxGluteo(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[checkBoxGluteo] = selected
        }
    }

    //Switch por si quiere hacer cardio
    val getSwitchCardio: Flow<Boolean?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[switchCardio] ?: false
        }

    suspend fun setSwitchCardio(selected : Boolean) {
        context.dataStoreSettings.edit { preferences ->
            preferences[switchCardio] = selected
        }
    }

    //Tiempo de cardio en minutos
    val getSwitchCardioTime: Flow<Int?> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[cardioTime] ?: 30
        }

    suspend fun setSwitchCardio(time : Int) {
        context.dataStoreSettings.edit { preferences ->
            preferences[cardioTime] = time
        }
    }








}

