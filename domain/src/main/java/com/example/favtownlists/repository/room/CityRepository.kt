package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityModel
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getData(): Flow<List<CityModel>>
}