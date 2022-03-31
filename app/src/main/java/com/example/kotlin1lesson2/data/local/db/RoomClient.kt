package com.example.kotlin1lesson2.data.local.db

import android.content.Context
import androidx.room.Room
import com.example.kotlin1lesson2.data.local.db.daos.CharacterDao

class RoomClient {

    fun provideCreateAppDatabase(context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "database-name"
    ).fallbackToDestructiveMigration()
        .build()

    fun  provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()
}