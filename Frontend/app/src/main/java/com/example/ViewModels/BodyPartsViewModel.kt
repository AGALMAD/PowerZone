package com.example.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.Models.BodyParts
import com.example.gymapp.Repositories.BodyPartsRepository
import kotlinx.coroutines.launch

class BodyPartsViewModel : ViewModel() {
    private val repository = BodyPartsRepository()

    private val _bodyParts = MutableLiveData<List<BodyParts>>()
    val bodyParts: LiveData<List<BodyParts>> = _bodyParts

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