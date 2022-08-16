package com.example.favtownlists.data.data_source

import com.example.favtownlists.data.data_source.mappers.toCityEntity
import com.example.favtownlists.data.data_source.mappers.toCityListEntity
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel

object InitCustomList {
    val initCustomList: CustomList = CustomList(
        customCityList = CustomCityListModel(name = "Европа").toCityListEntity(),
        cities = listOf(
            CityModel(name = "Париж", foundingDate = "III век до н. э."),
            CityModel(name = "Москва", foundingDate = "1147"),
            CityModel(name = "Мадрид", foundingDate = "852"),
            CityModel(name = "Рим", foundingDate = "753 до н. э."),
            CityModel(name = "Барселона", foundingDate = "III век до н. э."),
            CityModel(name = "Флоренция", foundingDate = "59 год до н. э"),
            CityModel(name = "Санкт-Петербург", foundingDate = "1703"),
            CityModel(name = "Прага", foundingDate = "VIII век"),
            CityModel(name = "Киев", foundingDate = "VI/VII—X века"),
        ).map { it.toCityEntity() }
    )
}