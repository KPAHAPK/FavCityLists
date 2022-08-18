package com.example.favtownlists.data.data_source

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.citylist.InitCityListInfo
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRefEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Provider

internal class RoomCallBack(
    private val daoProvider: Provider<MainDao>
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val europeCityList = InitCityList().initEuropeCityList
        val cityListInfoForInsert = InitCityListInfo().initCityListInfo
        val otherCityList = InitCityList().initOtherCityList
        val cityListFlow = daoProvider.get().getBanchOfCities()
        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            daoProvider.get().apply {
                insertBanchOfCities(europeCityList)
                insertCityListInfo(cityListInfoForInsert)
                val cityListInfo: CityListInfoEntity =
                    getCityListInfo(InitCityListInfo().initCityListInfo.name)
                launch {
                    cityListFlow.collect { cityList ->
                        for (city in cityList) {
                            insertCrossRef(CustomListCrossRefEntity(city.id!!, cityListInfo.id!!))
                        }
                    }
                }
                insertBanchOfCities(otherCityList)
            }
        }
    }
}