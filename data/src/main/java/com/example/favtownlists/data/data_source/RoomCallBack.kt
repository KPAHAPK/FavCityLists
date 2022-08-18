package com.example.favtownlists.data.data_source

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.citylist.InitCityListInfo
import kotlinx.coroutines.*
import javax.inject.Provider

class RoomCallBack(
    private val daoProvider: Provider<MainDao>
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val europeCityList = InitCityList().initEuropeCityList
        val cityListInfo = InitCityListInfo().initCityListInfo
        val otherCityList = InitCityList().initOtherCityList
        CoroutineScope(Dispatchers.Default).launch() {
            daoProvider.get().apply {
                insertCities(europeCityList)
                insertCityListInfo(cityListInfo)
                insertCities(otherCityList)
            }
        }
    }
}