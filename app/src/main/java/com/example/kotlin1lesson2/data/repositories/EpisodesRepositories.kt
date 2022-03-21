package com.example.kotlin1lesson2.data.repositories

import androidx.lifecycle.liveData
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.data.remote.apiservices.EpisodesApiService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EpisodesRepositories @Inject constructor(private val service: EpisodesApiService) {

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(service.fetchEpisodes()))
        } catch (ioException: Exception) {
            emit(Resource.Error(ioException.localizedMessage, null))
        }
    }
}