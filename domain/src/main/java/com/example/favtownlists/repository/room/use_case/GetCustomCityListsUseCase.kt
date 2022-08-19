package com.example.favtownlists.repository.room.use_case

import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CustomCityListModel
import kotlinx.coroutines.flow.Flow

class GetCustomCityListsUseCase(
    private val repository: CityRepository
) {
    suspend operator fun invoke(): Flow<List<CustomCityListModel>>{
        return repository.getAllCustomLists()
    }
}