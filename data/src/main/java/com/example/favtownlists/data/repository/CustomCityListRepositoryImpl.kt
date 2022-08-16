package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.AppDataBase
import com.example.favtownlists.data.data_source.mappers.toCityListEntity
import com.example.favtownlists.data.data_source.mappers.toCityListModel
import com.example.favtownlists.repository.room.CustomCityListRepository
import com.example.favtownlists.repository.room.model.CustomCityListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomCityListRepositoryImpl @Inject constructor(
    val db: AppDataBase
) : CustomCityListRepository {

    private val dao = db.customCityListDao()

    override suspend fun getData(name: String): CustomCityListModel {
        return dao.getCityList(name).toCityListModel()
    }

    override suspend fun saveToDB(cityListModel: CustomCityListModel) {
        return dao.insertCityList(cityListModel.toCityListEntity())
    }

    override suspend fun getAllList(): Flow<List<CustomCityListModel>> {
        return dao.getAllList()
            .map { entityList -> entityList.map { entity -> entity.toCityListModel() } }
    }
}