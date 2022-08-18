package com.example.favtownlists.repository.room.use_case

import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.model.CustomListCrossRefModel

class AddCustomCityListUseCase(
    private val repository: CityRepository
) {
    @Throws(InvalidCustomCityListException::class)
    suspend operator fun invoke(customCityListModel: CustomCityListModel){
        customCityListModel.also {
            if (it.cityListInfo.name.isBlank()){
                throw InvalidCustomCityListException("The name of the list can't be empty")
            }
            if (it.cityListInfo.shortName.isBlank()){
                throw InvalidCustomCityListException("The short name of the list can't be empty")
            }
        }
        repository.insertCityListInfo(customCityListModel.cityListInfo)
        val cityListId = customCityListModel.cityListInfo.id
        for (city in customCityListModel.cities){
            repository.insertCrossRef(CustomListCrossRefModel(city.id!!, cityListId!!))
        }
    }
}
class InvalidCustomCityListException(message: String): Exception(message)