package com.example.favcitylists.repository.room.use_case

import com.example.favcitylists.repository.room.CityRepository
import com.example.favcitylists.repository.room.model.CityModel

class GetCityListUseCase(
    private val repository: CityRepository
){
    suspend operator fun invoke(): List<CityModel> {
        return repository.getAllCities()
    }
}