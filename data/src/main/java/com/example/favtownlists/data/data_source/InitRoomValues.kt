package com.example.favtownlists.data.data_source

import android.graphics.Color
import com.example.favtownlists.data.data_source.city.CityEntity
import com.example.favtownlists.data.data_source.citylist.CityListInfoEntity
import com.example.favtownlists.data.data_source.crossref.CustomListCrossRefEntity
import com.example.favtownlists.data.data_source.mappers.toCityEntity
import com.example.favtownlists.data.data_source.mappers.toCityListInfoEntity
import com.example.favtownlists.data.data_source.mappers.toCustomCityList
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel


internal class InitCityList {
    val initEuropeCityList: List<CityEntity> = listOf(
        CityModel(name = "Париж", foundingDate = "III век до н. э."),
        CityModel(name = "Москва", foundingDate = "1147"),
        CityModel(name = "Мадрид", foundingDate = "852"),
        CityModel(name = "Киев", foundingDate = "VI/VII—X века"),
        CityModel(name = "Санкт-Петербург", foundingDate = "1703"),
    ).map { it.toCityEntity() }
    val initOtherCityList: List<CityEntity> = listOf(
        CityModel(name = "Лондон", foundingDate = "47 год"),
        CityModel(name = "Нью-Йорк", foundingDate = "1624"),
        CityModel(name = "Токио", foundingDate = "XII век"),
        CityModel(name = "Дубай", foundingDate = "1833"),
        CityModel(name = "Сингапур", foundingDate = "III век"),
        CityModel(name = "Сидней", foundingDate = "1788"),
        CityModel(name = "Рим", foundingDate = "753 до н. э."),
        CityModel(name = "Флоренция", foundingDate = "59 год до н. э"),
        CityModel(name = "Барселона", foundingDate = "III век до н. э."),
        CityModel(name = "Прага", foundingDate = "VIII век"),
    ).map { it.toCityEntity() }
}


internal class InitCityListInfo {
    val initCityListInfo: CityListInfoEntity = CityListInfoModel(
        name = "Любимые города в Европе",
        shortName = "Европа",
        color = Color.GREEN
    ).toCityListInfoEntity()
}

internal class InitCrossRef {
    private fun get(): List<CustomListCrossRefModel> {
        val europeCityList = InitCityList().initEuropeCityList
        val customList = mutableListOf<CustomListCrossRefModel>()
        for (i in 1..europeCityList.size) {
            customList.add(CustomListCrossRefModel(i, 1))
        }
        return customList
    }

    val initCrossRef: List<CustomListCrossRefEntity> = get().map { it.toCustomCityList() }
}