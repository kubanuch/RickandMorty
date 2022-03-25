package com.example.kotlin1lesson2.ui.fragments.locations

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.databinding.FragmentLocationsBinding
import com.example.kotlin1lesson2.ui.adapters.LoaderAdapter
import com.example.kotlin1lesson2.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations) {

    override val binding by viewBinding(FragmentLocationsBinding::bind)
    override val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter = LocationAdapter()


    override fun initialize() {
        binding.recyclerviewLocations.adapter =
            locationAdapter.withLoadStateFooter(LoaderAdapter { locationAdapter.retry() })
    }

    override fun setupObserves() {
        subscribeToLocation()
    }

    private fun subscribeToLocation() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }
}