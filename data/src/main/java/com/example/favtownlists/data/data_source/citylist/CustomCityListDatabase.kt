package com.example.favtownlists.data.data_source.citylist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CustomCityListEntity::class], version = 1, exportSchema = true)
abstract class CustomCityListDatabase: RoomDatabase() {
    abstract fun cityListDao(): CustomCityListDao

    companion object{
        const val DATABASE_NAME = "city_list_db"
    }
}