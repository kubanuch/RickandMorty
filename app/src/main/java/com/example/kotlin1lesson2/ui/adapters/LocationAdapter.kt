package com.example.kotlin1lesson2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson2.base.BaseDiffUtil
import com.example.kotlin1lesson2.databinding.ItemLocationsBinding
import com.example.kotlin1lesson2.models.RickAndMortyLocations

class LocationAdapter :
    PagingDataAdapter<RickAndMortyLocations, LocationAdapter.LocationViewHolder>(
        BaseDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LocationViewHolder =
        LocationViewHolder(ItemLocationsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }
    class LocationViewHolder(private val binding: ItemLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: RickAndMortyLocations) {
            binding.tvLocations.text = model.name
            binding.tvLocationsOne.text = model.type
            binding.tvLocationsTwo.text = model.dimension
        }
    }
}
