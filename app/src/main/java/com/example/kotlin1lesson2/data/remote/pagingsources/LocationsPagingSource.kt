package com.example.kotlin1lesson2.data.remote.pagingsources

import com.example.kotlin1lesson2.base.BasePagingSource
import com.example.kotlin1lesson2.data.remote.apiservices.LocationsApiService
import com.example.kotlin1lesson2.models.RickAndMortyLocations

class LocationsPagingSource(private val service: LocationsApiService) :
    BasePagingSource<RickAndMortyLocations>
        ({ service.fetchLocations(it) })