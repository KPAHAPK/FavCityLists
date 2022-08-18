package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.CityListsDataBase
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.mappers.toCityEntity
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.data.data_source.mappers.toCityModel
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityListRepositoryImpl @Inject constructor(
    private val db: CityListsDataBase
) : CityRepository {

    val dao = db.mainDao

    override fun getBanchOfCities() : Flow<List<CityModel>> {
        val entityFlow: Flow<List<CityEntity>> = dao.getBanchOfCities()
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

    override fun getBanchOfCityListInfo(): Flow<List<CityListInfoModel>> {
        TODO("Not yet implemented")
    }
}