package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityModel

interface CityRepository {
    suspend fun getData(): List<CityModel>
}