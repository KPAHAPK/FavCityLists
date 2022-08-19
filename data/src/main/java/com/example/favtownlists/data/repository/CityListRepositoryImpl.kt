package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.CityListsDataBase
import com.example.favtownlists.data.data_source.CustomCityList
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.mappers.toCityEntity
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.data.data_source.mappers.toCityModel
import com.example.favtownlists.data.data_source.mappers.toCustomCityListModel
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityListRepositoryImpl @Inject constructor(
    private val db: CityListsDataBase
) : CityRepository {

    val dao = db.mainDao

    override fun getAllCities() : Flow<List<CityModel>> {
        val entityFlow: Flow<List<CityEntity>> = dao.getAllCitiesFlow()
        val modelFlow = entityFlow.map { entityList ->
            entityList.map { city->
                city.toCityModel()
            }
        }
        return modelFlow
    }

    override suspend fun insertCity(city: CityModel) {
        dao.insertCity(city.toCityEntity())
    }

    override suspend fun getCityListInfo(name: String): CityListInfoModel {
        TODO("Not yet implemented")
    }

    override suspend fun insertCityListInfo(cityListInfoModel: CityListInfoModel) {
        dao.insertCityListInfo(cityListInfoModel.toCityListInfoEntity())
    }

    override fun getAllCityListInfo(): Flow<List<CityListInfoModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomListById(id: Int): CustomCityListModel {
        val customCityListModel = dao.getCustomListById(id).toCustomCityListModel()
        return customCityListModel
    }

    override suspend fun insertCrossRef(crossRef: CustomListCrossRefModel) {
        TODO("Not yet implemented")
    }

    override fun getAllCustomLists(): Flow<List<CustomCityListModel>> {
        TODO("Not yet implemented")
    }
}