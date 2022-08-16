package com.example.favtownlists.data.data_source.citylist

import com.example.favtownlists.data.data_source.city.InitCityList
import com.example.favtownlists.repository.room.model.CitiesInList
import com.example.favtownlists.repository.room.model.CustomCityListModel

class InitCustomCityList {
    val initMyCityList: List<CustomCityListModel> = listOf(
        CustomCityListModel(
            name = "Европа",
            content = CitiesInList(listOf(InitCityList().initCityList[1]))
        ),
    )
}