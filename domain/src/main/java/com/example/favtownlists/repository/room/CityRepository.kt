package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getBanchOfCities(): Flow<List<CityModel>>
    suspend fun insertCity(city: CityModel)
    suspend fun getCityListInfo(name: String) : CityListInfoModel
    suspend fun insertCityListInfo(cityListInfoModel: CityListInfoModel)
    fun getBanchOfCityListInfo(): Flow<List<CityListInfoModel>>
    suspend fun insertCrossRef(crossRef: CustomListCrossRefModel)
}