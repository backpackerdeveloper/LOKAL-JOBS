package com.shubhamtripz.lokaljobs.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.shubhamtripz.lokaljobs.data.dao.BookmarkedJobDao
import com.shubhamtripz.lokaljobs.data.entity.BookmarkedJob

@Database(entities = [BookmarkedJob::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarkedJobDao(): BookmarkedJobDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lokaljobs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
