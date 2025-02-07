package com.example.gymapp.GymApi.ViewModels.Learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.GymApi.Models.Exercises.Exercise
import com.example.gymapp.GymApi.Repositories.ExercisesRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExercisesViewModel: ViewModel() {
    private val repository = ExercisesRepository()

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercises

    fun fetchExercises(id : Int) {
        viewModelScope.launch {
            try {
                val exercises = repository.getExercises(id)
                _exercises.value = exercises
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}