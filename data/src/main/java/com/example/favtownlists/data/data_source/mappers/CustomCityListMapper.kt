package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.CustomCityList
import com.example.favtownlists.repository.room.model.CustomCityListModel

fun CustomCityListModel.toCustomCityLis(): CustomCityList {
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
