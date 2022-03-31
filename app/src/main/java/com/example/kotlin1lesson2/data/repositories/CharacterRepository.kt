package com.example.kotlin1lesson2.data.repositories

import com.example.kotlin1lesson2.base.BaseRepository
import com.example.kotlin1lesson2.data.local.db.daos.CharacterDao
import com.example.kotlin1lesson2.data.remote.apiservices.CharacterApiService
import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService,
    private val characterDao: CharacterDao
) :
    BaseRepository() {

    fun fetchCharacters(page: Int) = doRequest {
        service.fetchCharacters(page)
    }

    fun fetchCharacterID(id: Int) = doRequest {
        service.fetchCharacterId(id)

    }

    suspend fun insertCharacters(characters: List<RickAndMortyCharacters>) {
        characterDao.insertAll(*characters.toTypedArray())
    }

    fun getCharacters() = doRequest {
    characterDao.getAll()
    }

}

