package com.example.favtownlists.data.data_source.citylist

import android.content.res.Resources
import com.example.favtownlists.data.R
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.repository.room.model.CityListInfoModel

class InitCityListInfo {
    val initCityListInfo: CityListInfoEntity = CityListInfoModel(
        name = "Европа"
    ).toCityListInfoEntity()
}