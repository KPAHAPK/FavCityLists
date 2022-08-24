package com.example.favcitylists.repository.room.use_case

data class UseCases(
    val getCustomCityListUseCase: GetCustomCityListUseCase,
    val getCustomCityListsUseCase: GetCustomCityListsUseCase,
    val getCityListUseCase: GetCityListUseCase,
    val addCustomCityListUseCase: AddCustomCityListUseCase,
)
