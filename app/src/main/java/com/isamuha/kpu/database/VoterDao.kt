package com.isamuha.kpu.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VoterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(voter: Voter)

    @Update
    suspend fun update(voter: Voter)

    @Delete
    suspend fun delete(voter: Voter)

    @Query("SELECT * FROM voter_table ORDER BY id ASC")
    fun getAllVoters(): LiveData<List<Voter>>
}