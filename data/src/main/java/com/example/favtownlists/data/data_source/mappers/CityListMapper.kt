package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.citylist.CityListEntity
import com.example.favtownlists.repository.room.model.CityListModel

fun CityListModel.toCityListEntity(): CityListEntity{
    return CityListEntity(
        name = name,
        cities = content
    )
}
fun CityListEntity.toCityListModel(): CityListModel{
    return CityListModel(
        name = name,
        content = cities
    )
}