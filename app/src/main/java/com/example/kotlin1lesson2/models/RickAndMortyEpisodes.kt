package com.example.kotlin1lesson2.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyEpisodes(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("episode")
    val episode: String,
)

