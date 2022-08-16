package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.citylist.CustomCityListEntity
import com.example.favtownlists.repository.room.model.CustomCityListModel

fun CustomCityListModel.toCityListEntity(): CustomCityListEntity{
    return CustomCityListEntity(
        name = name,
        cities = content
    )
}
fun CustomCityListEntity.toCityListModel(): CustomCityListModel{
    return CustomCityListModel(
        name = name,
        content = cities
    )
}