package com.example.favtownlists.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favtownlists.data.data_source.city.CityDao
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CustomCityListDao
import com.example.favtownlists.data.data_source.citylist.CustomCityListEntity
import com.example.favtownlists.data.data_source.crossref.CrossRefDao
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef

@Database(
    entities = [
        CityEntity::class,
        CustomCityListEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun customCityListDao(): CustomCityListDao

    companion object{
        const val DATABASE_NAME = "city_list_db"
    }
}