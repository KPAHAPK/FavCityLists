package com.example.favcitylists.repository.room.model

data class CustomCityListModel(
    val cityListInfo: CityListInfoModel,
    val cities: List<CityModel>
)