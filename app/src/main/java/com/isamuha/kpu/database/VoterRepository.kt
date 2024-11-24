package com.isamuha.kpu.database

import androidx.lifecycle.LiveData

class VoterRepository(private val voterDao: VoterDao) {

    val allVoters: LiveData<List<Voter>> = voterDao.getAllVoters()

    suspend fun insert(voter: Voter) {
        voterDao.insert(voter)
    }

    suspend fun update(voter: Voter) {
        voterDao.update(voter)
    }

    suspend fun delete(voter: Voter) {
        voterDao.delete(voter)
    }
}