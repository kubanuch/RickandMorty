package com.example.kotlin1lesson2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson2.databinding.ItemLoaderBinding

class LoaderAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoaderAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(loadState: LoadState) = with(binding) {
            loadStateRetry.isVisible = loadState !is LoadState.Loading
            loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
            loadStateProgress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                loadStateErrorMessage.text = loadState.error.localizedMessage
            }
            loadStateRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(ItemLoaderBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }
}