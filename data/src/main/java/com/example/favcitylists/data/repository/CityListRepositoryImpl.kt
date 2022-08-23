package com.example.favcitylists.data.repository

import com.example.favcitylists.data.data_source.CityListsDataBase
import com.example.favcitylists.data.data_source.mappers.*
import com.example.favcitylists.repository.room.CityRepository
import com.example.favcitylists.repository.room.model.CityListInfoModel
import com.example.favcitylists.repository.room.model.CityModel
import com.example.favcitylists.repository.room.model.CustomCityListModel
import com.example.favcitylists.repository.room.model.CustomListCrossRefModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityListRepositoryImpl @Inject constructor(
    private val db: CityListsDataBase
) : CityRepository {

    private val dao = db.mainDao

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

    override fun getCustomListById(id: Int): Flow<CustomCityListModel> {
        return dao.getCustomListById(id).map { it.toCustomCityListModel() }
    }

    override suspend fun insertCrossRef(crossRef: CustomListCrossRefModel) {
        return dao.insertCrossRef(crossRef.toCustomCityList())
    }

    override fun getAllCustomLists(): Flow<List<CustomCityListModel>> {
        return dao.getAllCustomLists().map { list -> list.map { item -> item.toCustomCityListModel() } }
    }
}