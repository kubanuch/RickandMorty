package com.example.kotlin1lesson2.data.repositories

import com.example.kotlin1lesson2.base.BaseRepository
import com.example.kotlin1lesson2.data.remote.apiservices.LocationsApiService
import javax.inject.Inject

class LocationsRepositories @Inject constructor(private val service: LocationsApiService) :
    BaseRepository() {

    fun fetchLocations(page: Int) = doRequest {
        service.fetchLocations(page)
    }
}