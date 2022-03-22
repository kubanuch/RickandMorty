package com.example.kotlin1lesson2.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacters(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
)
