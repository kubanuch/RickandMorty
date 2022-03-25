package com.example.kotlin1lesson2.models

import com.example.kotlin1lesson2.base.BaseDiffModel
import com.google.gson.annotations.SerializedName

class RickAndMortyLocations(

    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
) : BaseDiffModel
