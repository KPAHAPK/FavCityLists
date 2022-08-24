package com.example.favcitylists.repository.room.use_case

import com.example.favcitylists.repository.room.CityRepository
import com.example.favcitylists.repository.room.model.CustomCityListModel
import kotlinx.coroutines.flow.Flow

class GetCustomCityListUseCase(
    private val repository: CityRepository
){
    operator fun invoke(id: Int): Flow<CustomCityListModel> {
      return repository.getCustomListById(id)
  }
}