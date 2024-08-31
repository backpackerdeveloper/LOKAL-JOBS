// adapter/LoadStateViewHolder.kt
package com.shubhamtripz.lokaljobs.adapter

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.shubhamtripz.lokaljobs.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        // Show loading spinner during the loading state
        binding.progressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        // Show retry button and error message when there's an error
        binding.retryButton.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
        binding.errorMsg.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
    }
}
