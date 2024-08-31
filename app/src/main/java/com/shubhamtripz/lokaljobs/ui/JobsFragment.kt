package com.shubhamtripz.lokaljobs.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubhamtripz.lokaljobs.adapter.JobAdapter
import com.shubhamtripz.lokaljobs.adapter.LoadStateAdapter
import com.shubhamtripz.lokaljobs.databinding.FragmentJobsBinding
import com.shubhamtripz.lokaljobs.viewmodel.JobsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private val viewModel: JobsViewModel by viewModels()

    private val jobAdapter = JobAdapter { job ->
        val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
            putExtra("JOB_ID", job.id)
            putExtra("TITLE", job.title ?: "Title Not Mentioned")
            putExtra("PLACE", job.primary_details?.Place ?: "Place Not Mentioned")
            putExtra("SALARY", job.primary_details?.Salary ?: "Salary Not Mentioned")
            putExtra("PHONE", job.custom_link?.removePrefix("tel:") ?: "Phone Not Mentioned")
            putExtra("OPENINGS", job.openings_count ?: "Openings Not Mentioned")
            putExtra("QUALIFICATIONS", job.primary_details?.Qualification ?: "Qualification Not Mentioned")
            putExtra("EXPERIENCE", job.primary_details?.Experience ?: "Experience Not Mentioned")
            putExtra("NO_OF_APPLICATIONS", job.num_applications ?: "Applications Not Mentioned")
            putExtra("VIEWS", job.views ?: "Views Not Mentioned")
            putExtra("COMPANY_NAME", job.company_name ?: "Company Not Mentioned")
            putExtra("JOB_DESCRIPTION", job.other_details ?: "Description Not Mentioned")
            putExtra("WHATSAPP", job.whatsapp_no ?: "WhatsApp Not Mentioned")
            putExtra("JOB_ROLE", job.job_role ?: "Job Role Not Mentioned")
        }
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobsBinding.inflate(inflater, container, false).apply {
            viewModel = this@JobsFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val footerAdapter = LoadStateAdapter { jobAdapter.retry() }

        binding.recyclerViewJobs.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jobAdapter.withLoadStateFooter(footerAdapter)
        }

        lifecycleScope.launch {
            viewModel.jobFlow.collectLatest { pagingData ->
                jobAdapter.submitData(pagingData)
            }
        }

        return binding.root
    }
}
