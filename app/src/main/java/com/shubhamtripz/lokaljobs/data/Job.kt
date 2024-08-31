package com.shubhamtripz.lokaljobs.data

data class Job(
    val id: Int,
    val title: String,
    val primary_details: PrimaryDetails,
    val fee_details: FeeDetails,
    val custom_link: String,
    val openings_count: String,
    val qualification: String,
    val experience: String,
    val num_applications: String,
    val views: String,
    val company_name: String,
    val other_details: String,
    val whatsapp_no: String,
    val job_role: String
)

data class PrimaryDetails(
    val Place: String,
    val Salary: String,
    val Qualification: String,
    val Experience: String
)

data class FeeDetails(
    val V3: List<Any> // Adjust as needed
)
