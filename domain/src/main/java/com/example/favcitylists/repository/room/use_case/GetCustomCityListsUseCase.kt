package com.example.favcitylists.repository.room.use_case

import com.example.favcitylists.repository.room.CityRepository
import com.example.favcitylists.repository.room.model.CustomCityListModel
import kotlinx.coroutines.flow.Flow

class GetCustomCityListsUseCase(
    private val repository: CityRepository
) {
    operator fun invoke(): Flow<List<CustomCityListModel>>{
        return repository.getAllCustomLists()
    }
}