package com.shubhamtripz.lokaljobs.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class BookmarkedJob(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jobId: String,
    val title: String,
    val place: String,
    val salary: String,
    val phone: String,
    val openings: String,
    val qualifications: String,
    val experience: String,
    val noOfApplications: String,
    val views: String,
    val companyName: String,
    val jobDescription: String,
    val whatsapp: String,
    val jobRole: String
)
