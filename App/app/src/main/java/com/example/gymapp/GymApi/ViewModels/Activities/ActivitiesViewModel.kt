package com.example.gymapp.GymApi.ViewModels.Activities

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.Models.Activities.ActivitiesRepository
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.accessTokenSaved
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.authDataStore
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.emailSaved
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.refreshTokenSaved
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActivitiesViewModel( application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = application.baseContext

    val activitiesRepository = ActivitiesRepository()


    //Datos del usuario
    private val _email = MutableStateFlow<String?>("")
    val email: StateFlow<String?> = _email
    private val _accessToken = MutableStateFlow<String?>("")
    val accessToken: StateFlow<String?> = _accessToken
    private val _refreshToken = MutableStateFlow<String?>("")
    val refreshToken: StateFlow<String?> = _refreshToken

    //Todas las actividades disponibles
    private val _activities = MutableStateFlow<List<ActivityResponse>?>(null)
    val activities: StateFlow<List<ActivityResponse>?> = _activities
    //Todas las actividades a las que el usuario está apuntado
    private val _userActivities = MutableStateFlow<List<ActivityResponse>?>(null)
    val userActivities: StateFlow<List<ActivityResponse>?> = _userActivities


    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            context.authDataStore.data
                .collect { preferences ->
                    _email.value = preferences[emailSaved]
                    _accessToken.value = preferences[accessTokenSaved]
                    _refreshToken.value = preferences[refreshTokenSaved]
                }
        }
    }


    suspend fun getAllActivities() {
        val token = _accessToken.value
        if (token != null) {
            val activitiesList = activitiesRepository.getAllActivities(token)
            _activities.value = activitiesList
        } else {
            throw IllegalStateException("Access token is null")
        }
    }

    suspend fun getUserActivities() {
        val token = _accessToken.value
        if (token != null) {
            val userActivitiesList = activitiesRepository.getAllTargetedActivities(token)
            _userActivities.value = userActivitiesList
        } else {
            throw IllegalStateException("Access token is null")
        }
    }


    suspend fun createParticipation(activityId: String){
        val response = _accessToken.value?.let { activitiesRepository.newParticipation(it,activityId) }
        if (response != null)
        {
            getUserActivities()
        }

    }

    suspend fun deleteParticipation(activityId: String) {
        val response = accessToken.value?.let { activitiesRepository.delete(it, activityId) }
        if (response != null) {
            _userActivities.value = _userActivities.value?.filterNot { it.id == activityId }
        }
    }


}
