package com.example.favcitylists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favcitylists.repository.room.model.CustomCityListModel
import com.example.favcitylists.repository.room.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    useCase: UseCases,
) : ViewModel() {

    private var getCustomCityListJob: Job? = null
    val customCityListsSF: StateFlow<List<CustomCityListModel>> =
        useCase.getCustomCityListsUseCase().stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    private val _bottomSheetIsActive: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val bottomSheetIsActive: StateFlow<Boolean> = _bottomSheetIsActive.asStateFlow()

    private val _customCityListSF: MutableStateFlow<CustomCityListModel?> = MutableStateFlow(null)
    val customCityListSF: StateFlow<CustomCityListModel?> = _customCityListSF.asStateFlow()

    fun setCustomCityList(index: Int) {
        _customCityListSF.value = customCityListsSF.value[index]
    }

    fun setBottomSheetActive(isActive: Boolean) {
        _bottomSheetIsActive.value = isActive
    }

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