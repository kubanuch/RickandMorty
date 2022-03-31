package com.example.kotlin1lesson2.ui.fragments.episodes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin1lesson2.base.BaseViewModel
import com.example.kotlin1lesson2.common.resource.Resource
import com.example.kotlin1lesson2.data.repositories.EpisodesRepositories
import com.example.kotlin1lesson2.models.RickAndMortyEpisodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodesRepositories,
) : BaseViewModel() {

    private var page = 0
    var isLoading: Boolean = false

    private val _episodesState = MutableLiveData<ArrayList<RickAndMortyEpisodes>>()
    val episodesState: LiveData<ArrayList<RickAndMortyEpisodes>> = _episodesState

    fun fetchEpisodes() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchEpisodes(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("anime", it.message.toString())
                    }
                    is Resource.Success -> {
                        isLoading = false
                        _episodesState.postValue(it.data?.result)
                        page++

                    }
                }
            }
        }
    }

    init {
        if (_episodesState.value == null) {
            fetchEpisodes()
        }
    }

}