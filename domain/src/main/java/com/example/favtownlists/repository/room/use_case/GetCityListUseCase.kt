package com.example.favtownlists.repository.room.use_case

import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityModel
import kotlinx.coroutines.flow.Flow

class GetCityListUseCase(
    private val repository: CityRepository
){
    suspend operator fun invoke(): Flow<List<CityModel>> {
        return repository.getAllCities()
    }
}