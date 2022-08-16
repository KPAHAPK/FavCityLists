package com.example.favtownlists.data.data_source.city

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1, exportSchema = true)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object{
        const val DATABASE_NAME = "city_db"
    }
}