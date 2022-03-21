package com.example.kotlin1lesson2.data.remote.apiservices

import com.example.kotlin1lesson2.models.RickAndMortyLocations
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import retrofit2.http.GET

interface LocationsApiService {
    @GET("api/location")
    suspend fun fetchLocations(): RickAndMortyResponse<RickAndMortyLocations>
}