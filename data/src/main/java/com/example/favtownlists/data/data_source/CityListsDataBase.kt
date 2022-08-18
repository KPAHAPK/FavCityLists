package com.example.favtownlists.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef

@Database(
    entities = [
        CityEntity::class,
        CityListInfoEntity::class,
        CustomListCrossRef::class
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