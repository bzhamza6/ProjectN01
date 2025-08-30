package com.example.projectn01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OpirationDao {
    @Insert
    suspend fun insertOperation(opiration: Operation)

    @Delete
    suspend fun delateOperation(operation: Operation)

    @Query("SELECT * FROM operations ORDER BY id DESC")
    fun getAllOpiration(): Flow<List<Operation>>

    @Query("DELETE FROM operations")
    suspend fun clearOpiration()
}
