package com.isamuha.kpu.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VoterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: VoterRepository
    val allVoters: LiveData<List<Voter>>

    init {
        val voterDao = VoterRoomDatabase.getDatabase(application).voterDao()
        repository = VoterRepository(voterDao)
        allVoters = repository.allVoters
    }

    fun insert(voter: Voter) = viewModelScope.launch {
        repository.insert(voter)
    }

    fun update(voter: Voter) = viewModelScope.launch {
        repository.update(voter)
    }

    fun delete(voter: Voter) = viewModelScope.launch {
        repository.delete(voter)
    }
}