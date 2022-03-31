package com.example.kotlin1lesson2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson2.base.BaseDiffUtil
import com.example.kotlin1lesson2.databinding.ItemEpisodesBinding
import com.example.kotlin1lesson2.models.RickAndMortyEpisodes

class EpisodesAdapter :
    ListAdapter<RickAndMortyEpisodes, EpisodesAdapter.EpisodesViewHolder>(BaseDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder =
        EpisodesViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

  inner class EpisodesViewHolder(private val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: RickAndMortyEpisodes) {
            binding.tvEpisodes.text = model.name
            binding.tvEpisodesOne.text = model.episode
        }
    }
}