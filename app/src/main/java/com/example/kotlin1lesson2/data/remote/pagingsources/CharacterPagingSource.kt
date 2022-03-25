package com.example.kotlin1lesson2.data.remote.pagingsources

import com.example.kotlin1lesson2.base.BasePagingSource
import com.example.kotlin1lesson2.data.remote.apiservices.CharacterApiService
import com.example.kotlin1lesson2.models.RickAndMortyCharacters

class CharacterPagingSource(private val service: CharacterApiService) :
    BasePagingSource<RickAndMortyCharacters>({ service.fetchCharacters(it) })
