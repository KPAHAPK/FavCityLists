package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityModel

interface CityListRepository {
    suspend fun getData(): List<CityModel>
}