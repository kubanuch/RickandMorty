package com.example.kotlin1lesson2.data.remote.apiservices

import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import com.example.kotlin1lesson2.models.RickAndMortyResponse
import retrofit2.http.GET

interface CharacterApiService {
    @GET("api/character")
    suspend fun fetchCharacters(): RickAndMortyResponse<RickAndMortyCharacters>
}