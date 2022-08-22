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
            if (it.cityListInfo.shortName.isBlank()){
                throw InvalidCustomCityListException("Короткое название не может быть пустым")
            }
            if (it.cityListInfo.name.isBlank()){
                throw InvalidCustomCityListException("Название не может быть пустым")
            }
            if (it.cities.isEmpty()){
                throw InvalidCustomCityListException("Выберите хотя бы 1 город")
            }
        }
        val cityListInfoId = repository.insertCityListInfo(customCityListModel.cityListInfo)
        for (city in customCityListModel.cities){
            repository.insertCrossRef(CustomListCrossRefModel(city.id!!, cityListInfoId.toInt()))
        }
    }
}
class InvalidCustomCityListException(message: String): Exception(message)