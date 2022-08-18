package com.example.favtownlists.repository.room.use_case

import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CustomCityListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCustomCityListUseCase(
    private val repository: CityRepository
){
  operator fun invoke(id: Int): Flow<CustomCityListModel> {
      return flow {
          val customCityList = repository.getCustomListById(id)
          emit(customCityList)
      }
  }
}