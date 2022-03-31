package com.example.kotlin1lesson2.ui.fragments.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.data.repositories.LocationsRepositories
import com.example.kotlin1lesson2.models.RickAndMortyLocations
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationsRepositories,
) : BaseViewModel() {

    private var page = 1
    var isLoading: Boolean = false
    private val _locationsState = MutableLiveData<RickAndMortyResponse<RickAndMortyLocations>>()
    val locationsState: LiveData<RickAndMortyResponse<RickAndMortyLocations>> = _locationsState

    private val _locationsLocaleState = MutableLiveData<List<RickAndMortyLocations>>()
    val episodesLocaleState: LiveData<List<RickAndMortyLocations>> = _locationsLocaleState

    fun fetchLocation() {
        isLoading = true
        repository.fetchLocations(page).collect(_locationsState) {
            page++
            isLoading = false
        }
    }

    fun getLocation() = repository.getLocation().collect(_locationsLocaleState, null)


}
