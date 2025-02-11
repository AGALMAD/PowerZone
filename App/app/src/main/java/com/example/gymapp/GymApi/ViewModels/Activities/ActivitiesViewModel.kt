package com.example.gymapp.GymApi.ViewModels.Activities

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.GymApi.Models.Activities.ActivityResponse
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.accessTokenSaved
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.authDataStore
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.emailSaved
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.refreshTokenSaved
import com.example.gymapp.GymApi.ViewModels.Auth.AuthViewModel.Companion.userNameSaved
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActivitiesViewModel( application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = application.baseContext

    //Datos del usuario
    private val _email = MutableStateFlow<String?>("")
    val email: StateFlow<String?> = _email
    private val _accessToken = MutableStateFlow<String?>("")
    val accessToken: StateFlow<String?> = _accessToken
    private val _refreshToken = MutableStateFlow<String?>("")
    val refreshToken: StateFlow<String?> = _refreshToken

    //Todas las actividades disponibles
    private val _activities = MutableStateFlow<List<ActivityResponse>>(emptyList())
    val activities: StateFlow<List<ActivityResponse>> = _activities
    //Todas las actividades a las que el usuario está apuntado
    private val _userActivities = MutableStateFlow<List<ActivityResponse>>(emptyList())
    val userActivities: StateFlow<List<ActivityResponse>> = _userActivities


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


    fun getAllActivities(){


    }

    fun getUserActivities(){


    }


    suspend fun createParticipation(activityId: String){

    }

    suspend fun deleteParticipation (activityId: String){

    }


}
