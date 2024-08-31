package com.shubhamtripz.lokaljobs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.shubhamtripz.lokaljobs.network.RetrofitInstance
import com.shubhamtripz.lokaljobs.paging.JobPagingSource

class JobsViewModel : ViewModel() {

    val jobFlow = Pager(PagingConfig(pageSize = 20)) {
        JobPagingSource(RetrofitInstance.api)
    }.flow.cachedIn(viewModelScope)
}