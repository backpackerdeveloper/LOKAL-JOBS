package com.shubhamtripz.lokaljobs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.shubhamtripz.lokaljobs.R
import com.shubhamtripz.lokaljobs.data.database.AppDatabase
import com.shubhamtripz.lokaljobs.data.entity.BookmarkedJob
import com.shubhamtripz.lokaljobs.databinding.ActivityDetailsBinding
import com.shubhamtripz.lokaljobs.viewmodel.DetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding

    private var isHeartSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Extract data from Intent and set it in the ViewModel
        viewModel.setTitle(intent.getStringExtra("TITLE") ?: "Title Not Mentioned")
        viewModel.setPlace(intent.getStringExtra("PLACE") ?: "Place Not Mentioned")
        viewModel.setSalary(intent.getStringExtra("SALARY") ?: "Salary Not Mentioned")
        viewModel.setPhone(intent.getStringExtra("PHONE") ?: "Phone Not Mentioned")
        viewModel.setOpenings(intent.getStringExtra("OPENINGS") ?: "Openings Not Mentioned")
        viewModel.setQualifications(intent.getStringExtra("QUALIFICATIONS") ?: "Qualifications Not Mentioned")
        viewModel.setExperience(intent.getStringExtra("EXPERIENCE") ?: "Experience Not Mentioned")
        viewModel.setNoOfApplications(intent.getStringExtra("NO_OF_APPLICATIONS") ?: "No. of Applications Not Mentioned")
        viewModel.setViews(intent.getStringExtra("VIEWS") ?: "Views Not Mentioned")
        viewModel.setCompanyName(intent.getStringExtra("COMPANY_NAME") ?: "Company Name Not Mentioned")
        viewModel.setJobDescription(intent.getStringExtra("JOB_DESCRIPTION") ?: "Job Description Not Mentioned")
        viewModel.setwhatsappno(intent.getStringExtra("WHATSAPP") ?: "WHATSAPP Not Mentioned")
        viewModel.setJobRole(intent.getStringExtra("JOB_ROLE") ?: "Job Role Not Mentioned")

        // -------- TOOLBAR CODE START -------
        val toolbar: Toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Handle back button click
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        // -------- TOOLBAR CODE END ---------

        // Handle heart button click
        binding.heartBtn.setOnClickListener {
            isHeartSelected = !isHeartSelected
            updateHeartIcon()
            if (isHeartSelected) {
                saveJobToBookmarks()
            }
        }

        // Initial heart icon state
        updateHeartIcon()
    }

    private fun saveJobToBookmarks() {
        val job = BookmarkedJob(
            jobId = intent.getStringExtra("JOB_ID") ?: "",
            title = viewModel.title.value ?: "Title Not Mentioned",
            place = viewModel.place.value ?: "Place Not Mentioned",
            salary = viewModel.salary.value ?: "Salary Not Mentioned",
            phone = viewModel.phone.value ?: "Phone Not Mentioned",
            openings = viewModel.openings.value ?: "Openings Not Mentioned",
            qualifications = viewModel.qualifications.value ?: "Qualifications Not Mentioned",
            experience = viewModel.experience.value ?: "Experience Not Mentioned",
            noOfApplications = viewModel.noOfApplications.value ?: "No. of Applications Not Mentioned",
            views = viewModel.views.value ?: "Views Not Mentioned",
            companyName = viewModel.companyName.value ?: "Company Name Not Mentioned",
            jobDescription = viewModel.jobDescription.value ?: "Job Description Not Mentioned",
            whatsapp = viewModel.whatsapp.value ?: "WHATSAPP Not Mentioned",
            jobRole = viewModel.jobrole.value ?: "Job Role Not Mentioned"
        )

        val dao = AppDatabase.getDatabase(this).bookmarkedJobDao()
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertJob(job)
            runOnUiThread {
                Toast.makeText(this@DetailsActivity, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateHeartIcon() {
        if (isHeartSelected) {
            binding.heartBtn.setImageResource(R.drawable.redheart)
        } else {
            binding.heartBtn.setImageResource(R.drawable.ic_heart)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
