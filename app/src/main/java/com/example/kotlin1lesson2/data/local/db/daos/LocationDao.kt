package com.example.kotlin1lesson2.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin1lesson2.models.RickAndMortyLocations


@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg locations: RickAndMortyLocations)

    @Query("SELECT *FROM rickandmortylocations ")
    suspend fun getAll(): List<RickAndMortyLocations>
}