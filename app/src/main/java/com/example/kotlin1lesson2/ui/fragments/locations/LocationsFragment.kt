package com.example.kotlin1lesson2.ui.fragments.locations

import android.util.Log
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.databinding.FragmentLocationsBinding
import com.example.kotlin1lesson2.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations) {

    override val binding by viewBinding(FragmentLocationsBinding::bind)
    override val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter = LocationAdapter()


    override fun initialize() {
        binding.recyclerviewLocations.adapter = locationAdapter
    }

    override fun setupObserves() {
        viewModel.fetchLocations().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.e("loo", "olo")
                }
                is Resource.Error -> {
                    Log.e("loe", "tol  ${it.message.toString()}")
                }
                is Resource.Success -> {
                    it.data?.let { it1 -> locationAdapter.setList(it1.result) }
                }
            }
        }
    }
}