package com.example.favtownlists.data.data_source.citylist

import android.graphics.Color
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.repository.room.model.CityListInfoModel

internal class InitCityListInfo {
    val initCityListInfo: CityListInfoEntity = CityListInfoModel(
        name = "Города в Европе",
        shortName = "Европа",
        color = Color.GREEN
    ).toCityListInfoEntity()
}