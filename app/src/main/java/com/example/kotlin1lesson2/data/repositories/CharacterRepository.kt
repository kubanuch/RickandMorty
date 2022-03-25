package com.example.kotlin1lesson2.data.repositories

import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.data.remote.apiservices.CharacterApiService
import com.example.kotlin1lesson2.data.remote.pagingsources.CharacterPagingSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) {

    fun fetchCharacters() = Pager(PagingConfig(pageSize = 40)) {
        CharacterPagingSource(service)
    }.flow

    fun fetchCharacterID(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(service.fetchCharacterId(id)))
        } catch (ioException: Exception) {
            emit(Resource.Error(ioException.localizedMessage, null))
        }
    }
}