package com.example.kotlin1lesson2.data.remote.apiservices

import com.example.kotlin1lesson2.models.RickAndMortyLocations
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApiService {
    @GET("api/location")
    suspend fun fetchLocations(
        @Query("page") page: Int,
    ): RickAndMortyResponse<RickAndMortyLocations>
}