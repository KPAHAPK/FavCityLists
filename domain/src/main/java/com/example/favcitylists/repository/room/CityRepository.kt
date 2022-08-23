package com.example.favcitylists.repository.room

import com.example.favcitylists.repository.room.model.CityListInfoModel
import com.example.favcitylists.repository.room.model.CityModel
import com.example.favcitylists.repository.room.model.CustomCityListModel
import com.example.favcitylists.repository.room.model.CustomListCrossRefModel
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getAllCities():List<CityModel>
    suspend fun insertCity(city: CityModel)
    suspend fun getCityListInfo(name: String) : CityListInfoModel
    suspend fun insertCityListInfo(cityListInfoModel: CityListInfoModel): Long
    suspend fun getAllCityListInfo(): Flow<List<CityListInfoModel>>
    fun getCustomListById(id: Int): Flow<CustomCityListModel>
    suspend fun insertCrossRef(crossRef: CustomListCrossRefModel)
    fun getAllCustomLists(): Flow<List<CustomCityListModel>>
}