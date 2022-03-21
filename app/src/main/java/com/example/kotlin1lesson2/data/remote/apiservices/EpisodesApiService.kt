package com.example.kotlin1lesson2.data.remote.apiservices

import com.example.kotlin1lesson2.models.RickAndMortyEpisodes
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import retrofit2.http.GET

interface EpisodesApiService {
    @GET("api/episode")
    suspend fun fetchEpisodes(): RickAndMortyResponse<RickAndMortyEpisodes>
}
