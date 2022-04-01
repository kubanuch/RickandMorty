package com.example.kotlin1lesson2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin1lesson2.data.local.db.daos.CharacterDao
import com.example.kotlin1lesson2.data.local.db.daos.EpisodesDao
import com.example.kotlin1lesson2.data.local.db.daos.LocationDao
import com.example.kotlin1lesson2.models.RickAndMortyCharacters
import com.example.kotlin1lesson2.models.RickAndMortyEpisodes
import com.example.kotlin1lesson2.models.RickAndMortyLocations

@Database(entities = [RickAndMortyCharacters::class, RickAndMortyLocations::class, RickAndMortyEpisodes::class],
    version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodesDao(): EpisodesDao
}