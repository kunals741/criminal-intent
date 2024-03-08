package com.example.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criminalintent.CrimeRepository
import com.example.criminalintent.models.Crime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()
    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(listOf())
    val crimes
        get() = _crimes.asStateFlow()

    init {
        viewModelScope.launch {
            crimeRepository.getCrimes().collect {
                _crimes.value = it
            }
        }
    }

    suspend fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}