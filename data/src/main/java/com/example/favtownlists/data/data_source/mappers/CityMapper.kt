package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.repository.room.model.CityModel

fun CityModel.toCityEntity(): CityEntity {
    return CityEntity(
        name = name,
        foundingDate = foundingDate
    )
}

fun CityEntity.toCityModel(): CityModel {
    return CityModel(
        id = id,
        name = name,
        foundingDate = foundingDate
    )
}
