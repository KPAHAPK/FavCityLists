package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.CityListsDataBase
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.repository.room.CityListInfoRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityListInfoRepositoryImpl @Inject constructor(
    val db: CityListsDataBase
) : CityListInfoRepository {
    override suspend fun getData(name: String): CityListInfoModel {
        TODO("Not yet implemented")
    }

    override suspend fun insert(cityListInfoModel: CityListInfoModel) {
        db.mainDao.insertCityListInfo(cityListInfoModel.toCityListInfoEntity())
    }

    override fun getAllList(): Flow<List<CityListInfoModel>> {
        TODO("Not yet implemented")
    }


}