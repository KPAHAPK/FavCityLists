package com.example.favcitylists.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favcitylists.data.data_source.city.CityEntity
import com.example.favcitylists.data.data_source.citylist.CityListInfoEntity
import com.example.favcitylists.data.data_source.crossref.CustomListCrossRefEntity

@Database(
    entities = [
        CityEntity::class,
        CityListInfoEntity::class,
        CustomListCrossRefEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CityListsDataBase : RoomDatabase() {
    abstract val mainDao: MainDao

    companion object {
        const val DATABASE_NAME = "citylistdb"
    }
}