package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.repository.room.model.CityListInfoModel

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