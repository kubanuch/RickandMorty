package com.example.kotlin1lesson2.data.remote.apiservices

import com.example.kotlin1lesson2.models.RickAndMortyEpisodes
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApiService {
    @GET("api/episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int,
    ): RickAndMortyResponse<RickAndMortyEpisodes>
}
