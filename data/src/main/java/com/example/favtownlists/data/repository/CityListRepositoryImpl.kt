package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.AppDataBase
import com.example.favtownlists.data.data_source.mappers.toCityModel
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityListRepositoryImpl @Inject constructor(
    val db: AppDataBase
) : CityRepository {
    override suspend fun getData() : Flow<List<CityModel>> {
        return flow {
            val cityList = db.mainDao().getCities()
            emit(cityList.map { it.toCityModel() })
        }
    }
}