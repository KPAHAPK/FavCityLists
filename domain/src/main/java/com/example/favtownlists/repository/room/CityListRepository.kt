package com.example.favtownlists.repository.room

import com.example.favtownlists.repository.room.model.CityListModel

interface CityListRepository {
    suspend fun getData(name: String)
    suspend fun saveToDB(cityListModel: CityListModel)
}