package com.shubhamtripz.lokaljobs.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.shubhamtripz.lokaljobs.data.entity.BookmarkedJob
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkedJobDao {

    @Insert
    suspend fun insertJob(job: BookmarkedJob)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarkedJobsFlow(): Flow<List<BookmarkedJob>>

    @Delete
    suspend fun deleteJob(job: BookmarkedJob)
}
