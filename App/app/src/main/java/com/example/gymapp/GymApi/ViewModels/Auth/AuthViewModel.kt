package com.example.gymapp.GymApi.ViewModels.Auth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.GymApi.Services.Auth.AuthService
import com.example.gymapp.GymApi.Services.Auth.UserService
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {


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
        auth.signInWithEmailAndPassword(email,password)
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



    fun singup(userName:String, email : String, password : String){

        if (userName.isEmpty()|| email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or passord can´t be empty")
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
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
