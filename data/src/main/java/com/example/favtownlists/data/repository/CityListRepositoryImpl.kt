package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.CityListsDataBase
import com.example.favtownlists.data.data_source.mappers.*
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityListRepositoryImpl @Inject constructor(
    private val db: CityListsDataBase
) : CityRepository {

    val dao = db.mainDao

    override suspend fun getAllCities(): List<CityModel> {
        return dao.getAllCities().map { it.toCityModel() }
    }

    override suspend fun insertCity(city: CityModel) {
        dao.insertCity(city.toCityEntity())
    }

    override suspend fun getCityListInfo(name: String): CityListInfoModel {
        TODO("Not yet implemented")
    }

    override suspend fun insertCityListInfo(cityListInfoModel: CityListInfoModel): Long {
        return dao.insertCityListInfo(cityListInfoModel.toCityListInfoEntity())
    }

    override suspend fun getAllCityListInfo(): Flow<List<CityListInfoModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomListById(id: Int): CustomCityListModel {
        return dao.getCustomListById(id).toCustomCityListModel()
    }

    override suspend fun insertCrossRef(crossRef: CustomListCrossRefModel) {
        return dao.insertCrossRef(crossRef.toCustomCityList())
    }

    override suspend fun getAllCustomLists(): Flow<List<CustomCityListModel>> {
        TODO("Not yet implemented")
    }
}