package com.example.kotlin1lesson2.ui.fragments.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.data.repositories.LocationsRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val repositories: LocationsRepositories) :
    BaseViewModel() {
    fun fetchLocations() = repositories.fetchLocations().cachedIn(viewModelScope)
}