package com.example.favtownlists.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.MainDao
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity

@Database(
    entities = [
        CityEntity::class,
        CityListInfoEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun mainDao(): MainDao

    companion object{
        const val DATABASE_NAME = "city_list_db"
    }
}