package com.example.favtownlists.data.data_source.citylist

import android.content.res.Resources
import com.example.favtownlists.data.R
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.repository.room.model.CityListInfoModel

object InitCityListInfo {
    val initCityListInfo: CityListInfoEntity = CityListInfoModel(
        name = Resources.getSystem().getString(R.string.init_europe_city_list_name)
    ).toCityListInfoEntity()
}