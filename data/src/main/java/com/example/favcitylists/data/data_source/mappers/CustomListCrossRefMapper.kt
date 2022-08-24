package com.example.favcitylists.data.data_source.mappers

import com.example.favcitylists.data.data_source.crossref.CustomListCrossRefEntity
import com.example.favcitylists.repository.room.model.CustomListCrossRefModel

fun CustomListCrossRefModel.toCustomCityList(): CustomListCrossRefEntity {
    return CustomListCrossRefEntity(
        cityId = cityId,
        cityListInfoId = cityListInfoId
    )
}

fun CustomListCrossRefEntity.toCustomCityListModel(): CustomListCrossRefModel {
    return CustomListCrossRefModel(
        cityId = cityId,
        cityListInfoId = cityListInfoId
    )
}
