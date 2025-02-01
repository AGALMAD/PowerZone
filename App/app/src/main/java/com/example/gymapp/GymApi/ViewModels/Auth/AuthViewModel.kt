package com.example.gymapp.GymApi.ViewModels.Auth
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.GymApi.Models.AuthenticationInstance
import com.example.gymapp.GymApi.Models.Exercises.RetrofitInstance
import com.example.gymapp.GymApi.Models.User.UserRequest
import com.example.gymapp.GymApi.Models.User.UserResponse
import com.example.gymapp.GymApi.Services.Auth.AuthService
import com.example.gymapp.GymApi.Services.Auth.UserService
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application:Application) : AndroidViewModel(application) {

    val authService = AuthenticationInstance.authService
    val userService = AuthenticationInstance.userService


    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if (auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        }
        else {
            _authState.value = AuthState.Authenticated
        }
    }


    fun login(email : String, password : String){

        if (email.isEmpty() || password.isEmpty()){
             _authState.value = AuthState.Error("Email or passord can´t be empty")
            return
        }

        _authState.value = AuthState.Loading
        authService.authenticate(email,password)
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
