package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CustomCityListModel

interface CustomCityListRepository {
    suspend fun getData(name: String) : CustomCityListModel
    suspend fun saveToDB(cityListModel: CustomCityListModel)
    suspend fun getAllList(): List<CustomCityListModel>
}