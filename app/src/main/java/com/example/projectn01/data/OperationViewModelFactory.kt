package com.example.projectn01.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OperationViewModelFactory (
    private val repository: OpirationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OperationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OperationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}