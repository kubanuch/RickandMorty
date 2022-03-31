package com.example.kotlin1lesson2.ui.fragments.locations

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.base.BaseFragment
import com.example.kotlin1lesson2.common.extensions.submitData
import com.example.kotlin1lesson2.databinding.FragmentLocationsBinding
import com.example.kotlin1lesson2.ui.adapters.LocationAdapter
import com.example.kotlin1lesson2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    R.layout.fragment_locations
) {

    override val binding by viewBinding(FragmentLocationsBinding::bind)
    override val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun setupObserves() {
        subscribeToLocation()
        subscribeToLocationLocale()
    }

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerviewLocations) {
        adapter = locationAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) viewModel.fetchLocation()
                else null
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToLocation() {
        viewModel.locationsState.observe(viewLifecycleOwner) {
            locationAdapter.submitData(it.result)

        }
    }

    private fun subscribeToLocationLocale() {
        viewModel.episodesLocaleState.observe(viewLifecycleOwner) {
            locationAdapter.submitData(it)
        }
    }

    override fun setupRequests() {
        if (viewModel.locationsState.value == null && isOnline()) viewModel.fetchLocation()
        else viewModel.getLocation()
    }

    fun isOnline(): Boolean {
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}