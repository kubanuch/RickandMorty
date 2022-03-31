package com.example.kotlin1lesson2.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.extensions.submitData
import com.example.kotlin1lesson2.databinding.FragmentLocationsBinding
import com.example.kotlin1lesson2.ui.adapters.LocationAdapter
import com.example.kotlin1lesson2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations
) {

    override val binding by viewBinding(FragmentLocationsBinding::bind)
    override val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun setupObserves() {
        subscribeToLocation()
    }

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerviewLocations) {
        adapter = locationAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, { viewModel.fetchLocations() }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationState.observe(viewLifecycleOwner) {
                locationAdapter.submitData(it)
            }
        }
    }
}