package com.example.projectn01.data

class OpirationRepository (private val dao: OpirationDao) {
    fun getAllOpiration() = dao.getAllOpiration()
    suspend fun insert(operation: Operation) = dao.insertOperation(operation)
    suspend fun clear() = dao.clearOpiration()
    suspend fun delate(operation: Operation) = dao.delateOperation(operation)
}