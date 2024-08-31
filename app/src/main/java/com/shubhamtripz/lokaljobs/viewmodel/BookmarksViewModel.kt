package com.shubhamtripz.lokaljobs.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.shubhamtripz.lokaljobs.data.database.AppDatabase
import com.shubhamtripz.lokaljobs.data.entity.BookmarkedJob
import kotlinx.coroutines.flow.Flow

class BookmarksViewModel(application: Application) : AndroidViewModel(application) {

    private val bookmarkedJobDao = AppDatabase.getDatabase(application).bookmarkedJobDao()

    val bookmarkedJobs: Flow<List<BookmarkedJob>> = bookmarkedJobDao.getAllBookmarkedJobsFlow()
}
