package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.repository.room.model.CityModel

fun CityEntity.toCityModel(): CityModel {
    return CityModel(
        name = name,
        foundingDate = foundingDate
    )
}