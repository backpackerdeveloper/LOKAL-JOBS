package com.shubhamtripz.lokaljobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubhamtripz.lokaljobs.data.Job
import com.shubhamtripz.lokaljobs.databinding.ItemJobBinding

class JobAdapter(
    private val onViewMoreClick: (Job) -> Unit // Pass a lambda function to handle view more clicks
) : PagingDataAdapter<Job, JobAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        if (job != null) {
            holder.bind(job, onViewMoreClick)
        }
    }

    class JobViewHolder(
        private val binding: ItemJobBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: Job, onViewMoreClick: (Job) -> Unit) {
            binding.apply {
                title.text = job.title ?: "Title Not Mentioned"
                location.text = job.primary_details?.Place ?: "Place Not Mentioned"
                salary.text = job.primary_details?.Salary ?: "Salary Not Mentioned"
                phone.text = job.custom_link?.removePrefix("tel:") ?: "Phone Not Mentioned"
                viewMore.setOnClickListener { onViewMoreClick(job) }
            }
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem == newItem
        }
    }
}
