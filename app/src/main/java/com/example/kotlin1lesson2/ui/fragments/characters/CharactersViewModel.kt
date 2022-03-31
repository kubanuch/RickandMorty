package com.example.kotlin1lesson2.ui.fragments.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.data.repositories.CharacterRepository
import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : BaseViewModel() {

    private var page = 0
    var isLoading: Boolean = false

    private val _characterState = MutableLiveData<ArrayList<RickAndMortyCharacters>>()
    val characterState: LiveData<ArrayList<RickAndMortyCharacters>> = _characterState

    fun fetchCharacter() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchCharacters(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("anime", it.message.toString())
                    }
                    is Resource.Success -> {
                        isLoading = false
                        it.data?.result?.let { data -> repository.insertCharacters(data) }
                        _characterState.postValue(it.data?.result)
                        page++
                    }
                }
            }
        }
    }

    init {
        if (_characterState.value == null)
            fetchCharacter()
    }

    fun getCharacters() = repository.getCharacters()

}