package com.isamuha.kpu.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voter_table")
data class Voter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val nik: String,
    val gender: String,
    val address: String
)