package com.example.kotlin1lesson2.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class RickAndMortyResponse<T>(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val result: ArrayList<T>,
)
