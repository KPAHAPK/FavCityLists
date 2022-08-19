package com.example.favtownlists.data.data_source.mappers

import com.example.favtownlists.data.data_source.CustomCityList
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRefEntity
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel

fun CustomListCrossRefModel.toCustomCityLis(): CustomListCrossRefEntity {
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
