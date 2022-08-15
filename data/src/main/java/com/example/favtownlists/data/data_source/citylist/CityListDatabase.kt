package com.example.favtownlists.data.data_source.citylist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityListEntity::class], version = 1, exportSchema = true)
abstract class CityListDatabase: RoomDatabase() {
    abstract fun cityListDao(): CityListDao

    companion object{
        const val DATABASE_NAME = "city_list_db"
    }
}