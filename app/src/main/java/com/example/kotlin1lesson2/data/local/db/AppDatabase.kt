package com.example.kotlin1lesson2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin1lesson2.data.local.db.daos.CharacterDao
import com.example.kotlin1lesson2.models.RickAndMortyCharacters

@Database(entities = [RickAndMortyCharacters::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao():CharacterDao
}