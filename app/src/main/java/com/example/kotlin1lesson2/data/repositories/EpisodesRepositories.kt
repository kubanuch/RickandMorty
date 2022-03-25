package com.example.kotlin1lesson2.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kotlin1lesson2.data.remote.apiservices.EpisodesApiService
import com.example.kotlin1lesson2.data.remote.pagingsources.EpisodesPagingSource
import javax.inject.Inject

class EpisodesRepositories @Inject constructor(private val service: EpisodesApiService) {

    fun fetchEpisodes() = Pager(PagingConfig(pageSize = 20)) {
        EpisodesPagingSource(service)
    }.flow

}