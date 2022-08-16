package com.example.favtownlists.data.data_source.citylist

import androidx.room.TypeConverter
import com.example.favtownlists.repository.room.model.CitiesInList
import com.google.gson.Gson


class CustomCityListConverter {
    @TypeConverter
    fun fromCityList(cityList: CitiesInList): String {
        return Gson().toJson(cityList)
    }

    @TypeConverter
    fun toCityList(data: String): CitiesInList {
        return Gson().fromJson(data, CitiesInList::class.java)
    }

}