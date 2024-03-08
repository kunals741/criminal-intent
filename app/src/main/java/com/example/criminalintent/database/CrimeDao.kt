package com.example.criminalintent.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.criminalintent.models.Crime
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM crime WHERE ID=(:id)")
    suspend fun getCrime(id: UUID): Crime

    @Update
    suspend fun updateCrime(crime: Crime)

    @Insert
    suspend fun addCrime(crime: Crime)

    @Query("DELETE FROM crime WHERE ID=(:id)")
    suspend fun deleteCrime(id:UUID)

}