package com.isamuha.kpu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Voter::class], version = 1, exportSchema = false)
abstract class VoterRoomDatabase : RoomDatabase() {
    abstract fun voterDao(): VoterDao
    companion object {
        @Volatile
        private var INSTANCE: VoterRoomDatabase? = null

        fun getDatabase(context: Context): VoterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VoterRoomDatabase::class.java,
                    "voter_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}