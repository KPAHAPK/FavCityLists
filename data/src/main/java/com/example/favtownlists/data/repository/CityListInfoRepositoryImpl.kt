package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.AppDataBase
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.data.data_source.mappers.toCityListModel
import com.example.favtownlists.repository.room.CityListInfoRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityListInfoRepositoryImpl @Inject constructor(
    val db: AppDataBase
) : CityListInfoRepository {
    override suspend fun getData(name: String): CityListInfoModel {
        TODO("Not yet implemented")
    }

    override suspend fun saveToDB(cityListModel: CityListInfoModel) {
        TODO("Not yet implemented")
    }

    override fun getAllList(): Flow<List<CityListInfoModel>> {
        TODO("Not yet implemented")
    }


}