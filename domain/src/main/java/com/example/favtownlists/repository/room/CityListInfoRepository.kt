package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityListInfoModel
import kotlinx.coroutines.flow.Flow

interface CityListInfoRepository {
    suspend fun getData(name: String) : CityListInfoModel
    suspend fun insert(cityListInfoModel: CityListInfoModel)
    fun getAllList(): Flow<List<CityListInfoModel>>
}