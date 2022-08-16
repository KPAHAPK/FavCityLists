package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.citylist.CityListDatabase
import com.example.favtownlists.data.data_source.mappers.toCityListEntity
import com.example.favtownlists.data.data_source.mappers.toCityListModel
import com.example.favtownlists.repository.room.CityListRepository
import com.example.favtownlists.repository.room.model.CityListModel
import javax.inject.Inject

class CityListRepositoryImpl @Inject constructor(
    val db: CityListDatabase
) : CityListRepository {

    private val dao = db.cityListDao()

    override suspend fun getData(name: String): CityListModel {
        return dao.getCityList(name).toCityListModel()
    }

    override suspend fun saveToDB(cityListModel: CityListModel) {
        return dao.insertCityList(cityListModel.toCityListEntity())
    }

    override suspend fun getAllList(): List<CityListModel> {
        return dao.getAllList().map { it.toCityListModel() }
    }
}