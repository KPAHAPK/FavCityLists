package com.example.favtownlists.data.data_source.repository

import com.example.favtownlists.data.data_source.citylist.CityListDatabase
import com.example.favtownlists.repository.room.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepositoryImpl @Inject constructor(
    val db: CityListDatabase
) : CityRepository {
    override suspend fun getData() {
        TODO("Not yet implemented")
    }
}