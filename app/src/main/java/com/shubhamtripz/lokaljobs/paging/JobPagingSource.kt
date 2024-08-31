package com.shubhamtripz.lokaljobs.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shubhamtripz.lokaljobs.data.Job
import com.shubhamtripz.lokaljobs.network.ApiService

class JobPagingSource(private val apiService: ApiService) : PagingSource<Int, Job>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getJobs(page)
            val jobs = response.results

            LoadResult.Page(
                data = jobs,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (jobs.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return null
    }
}
