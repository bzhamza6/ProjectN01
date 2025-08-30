package com.example.projectn01.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MyViewModel():ViewModel() {
    // قائمة العمليات (تتغير مع Compose)
    private val _history = mutableStateListOf<Operation>()
    val history: List<Operation> get() = _history

    // إضافة عملية جديدة مع منع التكرار
    fun addOperation(operation: Operation) {
        if (_history.isEmpty() || _history.last() != operation) {
            _history.add(operation)
        }
    }

    // حذف عملية معينة
    fun removeOperation(operation: Operation) {
        _history.remove(operation)
    }

    // حذف كل العمليات
    fun clearHistory() {
        _history.clear()
    }


}