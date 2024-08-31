package com.shubhamtripz.lokaljobs.network

import com.shubhamtripz.lokaljobs.data.Job
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobs(@Query("page") page: Int): JobResponse
}

data class JobResponse(
    val results: List<Job>
)
