package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.citylist.CustomCityListDatabase
import com.example.favtownlists.data.data_source.mappers.toCityListEntity
import com.example.favtownlists.data.data_source.mappers.toCityListModel
import com.example.favtownlists.repository.room.CustomCityListRepository
import com.example.favtownlists.repository.room.model.CustomCityListModel
import javax.inject.Inject

class CustomCityListRepositoryImpl @Inject constructor(
    val db: CustomCityListDatabase
) : CustomCityListRepository {

    private val dao = db.cityListDao()

    override suspend fun getData(name: String): CustomCityListModel {
        return dao.getCityList(name).toCityListModel()
    }

    override suspend fun saveToDB(cityListModel: CustomCityListModel) {
        return dao.insertCityList(cityListModel.toCityListEntity())
    }

    override suspend fun getAllList(): List<CustomCityListModel> {
        return dao.getAllList().map { it.toCityListModel() }
    }
}