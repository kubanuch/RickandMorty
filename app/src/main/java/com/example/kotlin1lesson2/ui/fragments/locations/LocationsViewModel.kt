package com.example.kotlin1lesson2.ui.fragments.locations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.data.repositories.LocationsRepositories
import com.example.kotlin1lesson2.models.RickAndMortyLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationsRepositories,
) : BaseViewModel() {

    private var page = 0
    var isLoading: Boolean = false

    private val _locationState = MutableLiveData<ArrayList<RickAndMortyLocations>>()
    val locationState: LiveData<ArrayList<RickAndMortyLocations>> = _locationState

    fun fetchLocations() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchLocations(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("anime", it.message.toString())
                    }
                    is Resource.Success -> {
                        isLoading = false
                        _locationState.postValue(it.data?.result)
                        page++

                    }
                }
            }
        }
    }

    init {
        if (_locationState.value == null) {
            fetchLocations()
        }
    }
}
