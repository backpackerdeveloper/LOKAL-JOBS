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
import com.shubhamtripz.lokaljobs.adapter.BookmarksAdapter
import com.shubhamtripz.lokaljobs.databinding.FragmentBookmarksBinding
import com.shubhamtripz.lokaljobs.viewmodel.BookmarksViewModel
import kotlinx.coroutines.launch

class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private val viewModel: BookmarksViewModel by viewModels()
    private val bookmarksAdapter = BookmarksAdapter(
        onClick = { job ->
            // Handle job click to view details
            val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
                putExtra("JOB_ID", job.jobId)
                putExtra("TITLE", job.title)
                putExtra("PLACE", job.place)
                putExtra("SALARY", job.salary)
                putExtra("PHONE", job.phone)
                putExtra("OPENINGS", job.openings)
                putExtra("QUALIFICATIONS", job.qualifications)
                putExtra("EXPERIENCE", job.experience)
                putExtra("NO_OF_APPLICATIONS", job.noOfApplications)
                putExtra("VIEWS", job.views)
                putExtra("COMPANY_NAME", job.companyName)
                putExtra("JOB_DESCRIPTION", job.jobDescription)
                putExtra("WHATSAPP", job.whatsapp)
                putExtra("JOB_ROLE", job.jobRole)
            }
            startActivity(intent)
        },
        onDeleteClick = { job ->
            // Handle delete click
            viewModel.deleteJob(job)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false).apply {
            viewModel = this@BookmarksFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setupRecyclerView()

        // Collect Flow in a coroutine scope
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookmarkedJobs.collect { jobs ->
                if (jobs.isEmpty()) {
                    binding.recyclerViewBookmarks.visibility = View.GONE
                    binding.textBookmarks.visibility = View.VISIBLE
                } else {
                    binding.recyclerViewBookmarks.visibility = View.VISIBLE
                    binding.textBookmarks.visibility = View.GONE
                    bookmarksAdapter.submitList(jobs)
                }
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewBookmarks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }
    }
}
