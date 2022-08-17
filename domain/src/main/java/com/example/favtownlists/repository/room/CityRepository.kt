package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityModel
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCities(): Flow<List<CityModel>>
    suspend fun insertCity(city: CityModel)
}