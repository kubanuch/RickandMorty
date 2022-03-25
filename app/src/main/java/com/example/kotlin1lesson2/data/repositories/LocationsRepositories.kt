package com.example.kotlin1lesson2.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kotlin1lesson2.data.remote.apiservices.LocationsApiService
import com.example.kotlin1lesson2.data.remote.pagingsources.LocationsPagingSource
import javax.inject.Inject

class LocationsRepositories @Inject constructor(private val service: LocationsApiService) {

    fun fetchLocations() = Pager(PagingConfig(pageSize = 20)) {
        LocationsPagingSource(service)
    }.flow
}