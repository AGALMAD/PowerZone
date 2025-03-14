package com.example.gymapp.GymApi.ViewModels.Auth
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.Appearance.Views.Dialog.AuthErrorType
import com.example.gymapp.GymApi.Models.Auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


private val dataStoreName = "gym_app_authentication";

class AuthViewModel( application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = application.baseContext

    companion object {
        val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(
            dataStoreName
        )

        //Datos del usuario
        val userNameSaved = stringPreferencesKey("userName")
        val emailSaved = stringPreferencesKey("email")
        val accessTokenSaved = stringPreferencesKey("accessToken")
        val refreshTokenSaved = stringPreferencesKey("refreshToken")


    }


    //Repositorio
    val auth = AuthRepository()

    //Variable para poder cambiar los estados
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    //Variable para poder verlos en las vistas
    var authState: StateFlow<AuthState> = _authState
    private val _userId = MutableStateFlow<String?>("")
    val userId: StateFlow<String?> = _userId
    private val _userName = MutableStateFlow<String?>("")
    val userName: StateFlow<String?> = _userName
    private val _email = MutableStateFlow<String?>("")
    val email: StateFlow<String?> = _email
    private val _accessToken = MutableStateFlow<String?>("")
    val accessToken: StateFlow<String?> = _accessToken
    private val _refreshToken = MutableStateFlow<String?>("")
    val refreshToken: StateFlow<String?> = _refreshToken



    init {
        loadData()
    }

    //Obtiene los datos del Data Store
    private fun loadData() {
        viewModelScope.launch {
            // Recuperar los datos del DataStore
            context.authDataStore.data
                .collect { preferences ->
                    _userName.value = preferences[userNameSaved]
                    _email.value = preferences[emailSaved]
                    _accessToken.value = preferences[accessTokenSaved]
                    _refreshToken.value = preferences[refreshTokenSaved]
                }
        }
    }

    suspend fun saveData(userName: String, email: String, accessToken: String, refreshToken: String) {

        _userName.value = userName
        _email.value = email
        _accessToken.value = accessToken
        _refreshToken.value = refreshToken

        context.authDataStore.edit { preferences ->
            preferences[userNameSaved] = userName
            preferences[emailSaved] = email
            preferences[accessTokenSaved] = accessToken
            preferences[refreshTokenSaved] = refreshToken
        }
    }


    fun login(email : String, password : String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error(AuthErrorType.EMPTY_CREDENTIALS)
            return
        }
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            val response = auth.login(email,password)

            if (response != null){
                getUserDataAndSave(response.accessToken,response.refreshToken)
                _authState.value = AuthState.Authenticated

            } else {
                _authState.value = AuthState.Error(AuthErrorType.INVALID_CREDENTIALS)
            }
        }

    }



    fun signup(userName: String, email: String, password: String) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error(AuthErrorType.EMPTY_CREDENTIALS)
            return
        }
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading

                val response = auth.signUp(email = email, name = userName, password = password)

                if (response != null) {
                    _email.value = response.email
                    _userName.value = response.name
                    _userId.value = response.id
                    //Inicia sesión automaticamente cuando se registra
                    login(email, password)
                }else {
                    _authState.value = AuthState.Error(AuthErrorType.INVALID_CREDENTIALS)
                 }
            }catch (e: IOException) {  // Error de red
                _authState.value = AuthState.Error(AuthErrorType.NETWORK_ERROR)
            } catch (e: Exception) {  // Otro tipo de error inesperado
                _authState.value = AuthState.Error(AuthErrorType.UNKNOWN_ERROR)
            }


        }
    }


    fun signout(){

        viewModelScope.launch {

            saveData("", "", "", "")
            _authState.value = AuthState.Unauthenticated
        }

    }

    suspend fun getUserDataAndSave(accessToken: String, refreshToken: String) {
        val response = auth.getAuthUser(accessToken)

        if (response != null) {
            saveData(response.name,
                response.email,
                accessToken,
                refreshToken)
        }
    }


    suspend fun refreshAndSaveToken(refreshToken: String){
        val response = auth.doRefreshAccessToken(refreshToken)

        if (response != null) {
            context.authDataStore.edit { preferences ->
                preferences[accessTokenSaved] = response.token
            }

            _authState.value = AuthState.Authenticated

        }

    }


}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val errorType: AuthErrorType) : AuthState()
}
