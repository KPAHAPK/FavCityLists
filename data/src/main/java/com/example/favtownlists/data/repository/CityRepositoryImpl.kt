package com.example.favtownlists.data.repository

import com.example.favtownlists.data.data_source.city.CityDatabase
import com.example.favtownlists.data.data_source.mappers.toCityModel
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    val db: CityDatabase
) : CityRepository {
    override suspend fun getData() : List<CityModel> {
        return db.cityDao().getCities().map { it.toCityModel() }
    }
}