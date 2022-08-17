package com.example.favtownlists.data.data_source

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.citylist.InitCityListInfo
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomCallBack(
    private val daoProvider: Provider<MainDao>
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            populateDb()
        }
    }

    private suspend fun populateDb(){
        daoProvider.get().apply {
            insertCities(InitCityList.initEuropeCityList)
            insertCityListInfo(InitCityListInfo.initCityListInfo)
//            val cityList: List<CityEntity> =
//                getCities().flatMapConcat { it.asFlow() }.toList()
//            val cityListInfo: Flow<CityListInfoEntity> =
//                getCityListInfo(InitCityListInfo.initCityListInfo.name).onEach {
//
//                }
//            for (city in cityList) {
//                insertCrossRef(CustomListCrossRef(city.id!!, cityListInfo.id!!))
//            }
            insertCities(InitCityList.intiOtherCityList)
        }
    }
}