package com.example.gymapp.GymApi.ViewModels.Auth
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.GymApi.Data.dataStoreName
import com.example.gymapp.GymApi.Models.AuthenticationInstance
import com.example.gymapp.GymApi.Models.Exercises.RetrofitInstance
import com.example.gymapp.GymApi.Models.User.UserRequest
import com.example.gymapp.GymApi.Models.User.UserResponse
import com.example.gymapp.GymApi.Repositories.AuthRepository
import com.example.gymapp.GymApi.Services.Auth.AuthService
import com.example.gymapp.GymApi.Services.Auth.UserService
import com.example.gymapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val dataStoreName = "gym_app_authentication";

class AuthViewModel(val application:Application) : AndroidViewModel(application) {



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

    val getUserName: Flow<String?> = application.baseContext.authDataStore.data
        .map { preferences ->
            preferences[userName] ?: application.baseContext.getString(R.string.name_label)
        }

    suspend fun setSwitchCardioTime(newUserName : String) {
        application.baseContext.authDataStore.edit { preferences ->
            preferences[userName] = newUserName
        }
    }

    val getEmail: Flow<String?> = application.baseContext.authDataStore.data
        .map { preferences ->
            preferences[email] ?: application.baseContext.getString(R.string.emailWord)
        }

    suspend fun setEmail(newEmail : String) {
        application.baseContext.authDataStore.edit { preferences ->
            preferences[email] = newEmail
        }
    }

    val getAccessToken: Flow<String?> = application.baseContext.authDataStore.data
        .map { preferences ->
            preferences[accessToken]
        }

    suspend fun setAccessToken(newAccessToken : String) {
        application.baseContext.authDataStore.edit { preferences ->
            preferences[accessToken] = newAccessToken
        }
    }

    val getRefreshToken: Flow<String?> = application.baseContext.authDataStore.data
        .map { preferences ->
            preferences[refreshToken]
        }

    suspend fun setRefreshToken(newRefreshToken : String) {
        application.baseContext.authDataStore.edit { preferences ->
            preferences[refreshToken] = newRefreshToken
        }
    }


    val auth = AuthRepository()


    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState


    init {
        //Cargar los datos del data store
    }




    fun login(email : String, password : String){

        if (email.isEmpty() || password.isEmpty()){
             _authState.value = AuthState.Error("Email or passord can´t be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.login(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                }
                else{
                    _authState.value =
                        AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }



    suspend fun singup(userName:String, email : String, password : String){

        if (userName.isEmpty()|| email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or passord can´t be empty")
        }

        _authState.value = AuthState.Loading

        val response = userService.create(UserRequest(email= email, name = userName, password = password))

        //Maneja la respuesta del servidor
        if (response != null) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.Error("Something went wrong")

    }


    fun singout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun getUserDataAndSave(email: String){

    }

}


sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val mesagge : String) : AuthState()
}
