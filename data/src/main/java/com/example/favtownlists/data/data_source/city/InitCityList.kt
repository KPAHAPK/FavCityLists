package com.example.favtownlists.data.data_source.city

import com.example.favtownlists.data.data_source.mappers.toCityEntity
import com.example.favtownlists.repository.room.model.CityModel

internal class InitCityList {
    val initEuropeCityList: List<CityEntity> = listOf(
        CityModel(name = "Париж", foundingDate = "III век до н. э."),
        CityModel(name = "Москва", foundingDate = "1147"),
        CityModel(name = "Мадрид", foundingDate = "852"),
        CityModel(name = "Киев", foundingDate = "VI/VII—X века"),
        CityModel(name = "Рим", foundingDate = "753 до н. э."),
        CityModel(name = "Флоренция", foundingDate = "59 год до н. э"),
        CityModel(name = "Санкт-Петербург", foundingDate = "1703"),
        CityModel(name = "Барселона", foundingDate = "III век до н. э."),
        CityModel(name = "Прага", foundingDate = "VIII век"),
    ).map { it.toCityEntity() }
    val initOtherCityList: List<CityEntity> = listOf(
        CityModel(name = "Лондон", foundingDate = "47 год"),
        CityModel(name = "Нью-Йорк", foundingDate = "1624"),
        CityModel(name = "Токио", foundingDate = "XII век"),
        CityModel(name = "Дубай", foundingDate = "1833"),
        CityModel(name = "Сингапур", foundingDate = "III век"),
        CityModel(name = "Сидней", foundingDate = "1788")
    ).map { it.toCityEntity() }
}