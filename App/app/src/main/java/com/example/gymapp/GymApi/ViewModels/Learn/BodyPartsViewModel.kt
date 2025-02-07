package com.example.gymapp.GymApi.ViewModels.Learn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.GymApi.Models.Exercises.BodyPart
import com.example.gymapp.GymApi.Repositories.BodyPartsRepository
import kotlinx.coroutines.launch

class BodyPartsViewModel : ViewModel() {
    private val repository = BodyPartsRepository()

    private val _bodyParts = MutableLiveData<List<BodyPart>>()
    val bodyParts: LiveData<List<BodyPart>> = _bodyParts

    fun fetchBodyParts() {
        viewModelScope.launch {
            try {
                val bodyParts = repository.getBodyParts()
                _bodyParts.value = bodyParts
            } catch (e: Exception) {
                // Handle error
            }
        }
    }


}