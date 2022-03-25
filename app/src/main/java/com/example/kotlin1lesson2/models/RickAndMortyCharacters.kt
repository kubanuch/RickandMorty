package com.example.kotlin1lesson2.models

import com.example.kotlin1lesson2.base.BaseDiffModel
import com.google.gson.annotations.SerializedName

data class RickAndMortyCharacters(

    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
) : BaseDiffModel
