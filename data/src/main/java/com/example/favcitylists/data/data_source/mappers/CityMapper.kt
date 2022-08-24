package com.example.favcitylists.data.data_source.mappers

import com.example.favcitylists.data.data_source.city.CityEntity
import com.example.favcitylists.repository.room.model.CityModel

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
