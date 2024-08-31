package com.shubhamtripz.lokaljobs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubhamtripz.lokaljobs.data.entity.BookmarkedJob
import com.shubhamtripz.lokaljobs.databinding.ItemJobBookmarksBinding

class BookmarksAdapter(
    private val onClick: (BookmarkedJob) -> Unit,
    private val onDeleteClick: (BookmarkedJob) -> Unit
) : RecyclerView.Adapter<BookmarksAdapter.BookmarkViewHolder>() {

    private var bookmarkedJobs: List<BookmarkedJob> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemJobBookmarksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarkedJobs[position])
    }

    override fun getItemCount(): Int = bookmarkedJobs.size

    fun submitList(list: List<BookmarkedJob>) {
        bookmarkedJobs = list
        notifyDataSetChanged()
    }

    inner class BookmarkViewHolder(private val binding: ItemJobBookmarksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: BookmarkedJob) {
            binding.title.text = job.title
            binding.location.text = job.place
            binding.salary.text = job.salary
            binding.phone.text = job.phone
            binding.viewMore.setOnClickListener { onClick(job) }
            binding.deleteBookmarks.setOnClickListener { onDeleteClick(job) }
        }
    }
}
