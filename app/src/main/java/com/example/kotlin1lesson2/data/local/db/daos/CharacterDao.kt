package com.example.kotlin1lesson2.data.local.db.daos


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin1lesson2.models.RickAndMortyCharacters


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: RickAndMortyCharacters)

    @Query("SELECT *FROM rickandmortycharacters")
     suspend fun getAll(): List<RickAndMortyCharacters>
}