package com.example.favcitylists.data.data_source

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

internal class RoomCallBack(
    private val daoProvider: Provider<MainDao>
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val europeCityList = InitCityList().initEuropeCityList
        val cityListInfo = InitCityListInfo().initCityListInfo
        val crossRef = InitCrossRef().initCrossRef
        val otherCityList = InitCityList().initOtherCityList
        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            daoProvider.get().apply {
                insertBanchOfCities(europeCityList)
                insertCityListInfo(cityListInfo)
                insertBanchOfCrossRefs(crossRef)
                insertBanchOfCities(otherCityList)
            }
        }
    }
}