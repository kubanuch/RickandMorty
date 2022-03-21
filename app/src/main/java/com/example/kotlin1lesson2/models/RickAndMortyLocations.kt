package com.example.kotlin1lesson2.models

import com.google.gson.annotations.SerializedName

class RickAndMortyLocations(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
)
