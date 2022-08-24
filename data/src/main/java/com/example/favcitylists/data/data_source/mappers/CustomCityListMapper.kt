package com.example.favcitylists.data.data_source.mappers

import com.example.favcitylists.data.data_source.CustomCityList
import com.example.favcitylists.repository.room.model.CustomCityListModel

fun CustomCityListModel.toCustomCityList(): CustomCityList {
    return CustomCityList(
        cities = cities.map { it.toCityEntity() },
        cityListInfo = cityListInfo.toCityListInfoEntity()
    )
}

fun CustomCityList.toCustomCityListModel(): CustomCityListModel {
    return CustomCityListModel(
        cities = cities.map { it.toCityModel() },
        cityListInfo = cityListInfo.toCityListModel()
    )
}
