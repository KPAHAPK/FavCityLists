package com.example.favtownlists.screens.newlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.use_case.InvalidCustomCityListException
import com.example.favtownlists.repository.room.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCustomCityListViewModel @Inject constructor(
    private val useCase: UseCases,
) : ViewModel() {
    private var getCustomCityListJob: Job? = null

    private val _cityListInfoSF: MutableStateFlow<CityListInfoModel> =
        MutableStateFlow(CityListInfoModel(null, "", "", 0))
    val cityListInfoSF: StateFlow<CityListInfoModel>
        get() = _cityListInfoSF

    private val _cityListSF: MutableStateFlow<List<CityModel>> =
        MutableStateFlow(listOf())
    val cityListSF: StateFlow<List<CityModel>>
        get() = _cityListSF

    private val _checkedCitySF: MutableStateFlow<List<CityModel>> = MutableStateFlow(listOf())
    val checkedCitySF: StateFlow<List<CityModel>>
        get() = _checkedCitySF.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getCityList()
    }

    fun updateCityListModel(cityListModel: CityListInfoModel) {
        _cityListInfoSF.value = cityListModel
    }

    fun addCheckedPosition(city: CityModel) {
        _checkedCitySF.value = _checkedCitySF.value + city
    }

    fun removeCheckedCity(city: CityModel) {
        _checkedCitySF.value = _checkedCitySF.value - city
    }

    fun insertToDatabase() {
        viewModelScope.launch {
            try {
                val cityListInfo = _cityListInfoSF.value
                val checkedCities = _checkedCitySF.value
                useCase.addCustomCityListUseCase(
                    CustomCityListModel(cityListInfo, checkedCities)
                )
                _eventFlow.emit(UIEvent.SaveCustomCityList)
            } catch (e: InvalidCustomCityListException) {
                val message = e.message ?: "Не могу сохранить список"
                _eventFlow.emit(UIEvent.ShowSnackBar(message))
            }
        }
    }

    private fun getCityList() {
        getCustomCityListJob?.cancel()
        viewModelScope.launch {
            val cityList = useCase.getCityListUseCase()
            _cityListSF.value = cityList
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        object SaveCustomCityList : UIEvent()
    }
}