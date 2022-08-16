package com.example.favtownlists.data.data_source.citylist

import androidx.room.TypeConverter
import com.example.favtownlists.repository.room.model.CityList
import com.google.gson.Gson


class CityListConverter {
    @TypeConverter
    fun fromCityList(cityList: CityList): String {
        return Gson().toJson(cityList)
    }
    @TypeConverter
    fun toCityList(data: String): CityList {
        return Gson().fromJson(data, CityList::class.java)
    }

}