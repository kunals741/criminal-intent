package com.example.criminalintent

import android.content.Context
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import com.example.criminalintent.database.migration_1_2
import com.example.criminalintent.database.migration_2_3
import com.example.criminalintent.models.Crime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID


private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )
        .addMigrations(migration_1_2, migration_2_3)
        .build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()

    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)

    fun updateCrime(crime: Crime) {
        coroutineScope.launch {
            database.crimeDao().updateCrime(crime)
        }
    }

    suspend fun addCrime(crime: Crime) = database.crimeDao().addCrime(crime)

    suspend fun deleteCrime(id: UUID) = database.crimeDao().deleteCrime(id)


    companion object {
        private var INSTANCE: CrimeRepository? = null

        //use to set the instance, from applicaiotn
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        //compontnt will use this to get the instance:
        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}