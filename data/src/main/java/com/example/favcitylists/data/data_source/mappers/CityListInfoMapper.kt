package com.example.favcitylists.data.data_source.mappers

import com.example.favcitylists.data.data_source.citylist.CityListInfoEntity
import com.example.favcitylists.repository.room.model.CityListInfoModel

fun CityListInfoModel.toCityListInfoEntity(): CityListInfoEntity{
    return CityListInfoEntity(
        name = name,
        shortName = shortName,
        color = color
    )
}
fun CityListInfoEntity.toCityListModel(): CityListInfoModel{
    return CityListInfoModel(
        id = id,
        name = name,
        shortName = shortName,
        color = color
    )
}