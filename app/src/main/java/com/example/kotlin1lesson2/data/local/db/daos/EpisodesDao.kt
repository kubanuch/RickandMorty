package com.example.kotlin1lesson2.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin1lesson2.models.RickAndMortyEpisodes


@Dao
interface EpisodesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg episodes: RickAndMortyEpisodes)

    @Query("SELECT *FROM rickandmortyepisodes ")
    suspend fun getAll(): List<RickAndMortyEpisodes>
}