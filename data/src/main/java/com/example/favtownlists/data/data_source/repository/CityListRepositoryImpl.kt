package com.example.favtownlists.data.data_source.repository

import com.example.favtownlists.data.data_source.citylist.CityListDatabase
import com.example.favtownlists.repository.room.CityListRepository
import com.example.favtownlists.repository.room.model.CityListModel
import javax.inject.Inject

class CityListRepositoryImpl @Inject constructor(
    val db: CityListDatabase
) : CityListRepository {
    override suspend fun getData(name: String) {
        db.cityListDao().getCityList()
    }

    override suspend fun saveToDB(cityListModel: CityListModel) {
        db.cityListDao().insertCityList(cityListModel)
    }
}