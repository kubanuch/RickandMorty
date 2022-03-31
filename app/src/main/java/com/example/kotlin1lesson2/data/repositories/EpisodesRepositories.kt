package com.example.kotlin1lesson2.data.repositories

import com.example.kotlin1lesson2.base.BaseRepository
import com.example.kotlin1lesson2.data.remote.apiservices.EpisodesApiService
import javax.inject.Inject

class EpisodesRepositories @Inject constructor(private val service: EpisodesApiService) :
    BaseRepository() {

    fun fetchEpisodes(page: Int) = doRequest {
        service.fetchEpisodes(page)
    }

}