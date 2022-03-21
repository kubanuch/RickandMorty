package com.example.kotlin1lesson2.ui.fragments.locations

import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.data.repositories.LocationsRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repositor:
    LocationsRepositories,
) : BaseViewModel() {
    fun fetchLocations() = repositor.fetchLocations()
}