package com.example.favtownlists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val useCase: UseCases,
) : ViewModel() {

    private var getCustomCityListJob: Job? = null

//    private val _customCityListSF: MutableSharedFlow<CustomCityListModel> = MutableSharedFlow(1)
//    val customCityListSF: SharedFlow<CustomCityListModel> = _customCityListSF.asSharedFlow()
//
//    private val _customCityListsSF: MutableStateFlow<List<CustomCityListModel>> = MutableStateFlow(
//        listOf()
//    )
    val customCityListsSF: StateFlow<List<CustomCityListModel>> =
        useCase.getCustomCityListsUseCase().stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    private val _cities: MutableStateFlow<List<CityModel>> = MutableStateFlow(listOf())
    val cities: StateFlow<List<CityModel>> = _cities.asStateFlow()

    private val _citiListInfoTab: MutableStateFlow<CityListInfoModel?> = MutableStateFlow(null)
    val citiListInfoTab: StateFlow<CityListInfoModel?> = _citiListInfoTab.asStateFlow()

    private val _bottomSheetIsActive: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val bottomSheetIsActive: StateFlow<Boolean> = _bottomSheetIsActive.asStateFlow()

    private val _customCityListSF: MutableStateFlow<CustomCityListModel?> = MutableStateFlow(null)
    val customCityListSF: StateFlow<CustomCityListModel?> = _customCityListSF.asStateFlow()

    fun setCities(cities: List<CityModel>){
        _cities.value = cities
    }

    fun setListInfoTab(citiListInfo: CityListInfoModel){
        _citiListInfoTab.value = citiListInfo
    }

    fun setCustomCityList(index: Int){
        _customCityListSF.value = customCityListsSF.value[index]
    }

    fun setBottomSheetActive(isActive: Boolean){
        _bottomSheetIsActive.value = isActive
    }



//    fun getCustomCityListById(id: Int) {
//        val list = _customCityListsSF.viewModelScope.launch {
//            _customCityListSF.emit()
//        }
//    }

    init {
        getCustomCityLists()
    }

    private fun getCustomCityLists() {
        getCustomCityListJob?.cancel()
        getCustomCityListJob = viewModelScope.launch {
            customCityListsSF.collect()
        }
    }
}