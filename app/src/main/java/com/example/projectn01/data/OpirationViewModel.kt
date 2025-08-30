package com.example.projectn01.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OperationViewModel(private val repository: OpirationRepository) : ViewModel() {

    // إذا كنت تستعمل Compose يفضل Flow بدل LiveData
    val operations = repository.getAllOpiration()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addOperation(operation: Operation) {
        viewModelScope.launch {
            try {
                repository.insert(operation)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteOperation(operation: Operation) {
        viewModelScope.launch {
            try {
                repository.delate(operation)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clearOperations() {
        viewModelScope.launch {
            try {
                repository.clear()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
