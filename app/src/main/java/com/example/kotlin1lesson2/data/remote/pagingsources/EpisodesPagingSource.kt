package com.example.kotlin1lesson2.data.remote.pagingsources

import com.example.kotlin1lesson2.base.BasePagingSource
import com.example.kotlin1lesson2.data.remote.apiservices.EpisodesApiService
import com.example.kotlin1lesson2.models.RickAndMortyEpisodes

class EpisodesPagingSource(private val service: EpisodesApiService) :
    BasePagingSource<RickAndMortyEpisodes>(
        { service.fetchEpisodes(it) }
    )