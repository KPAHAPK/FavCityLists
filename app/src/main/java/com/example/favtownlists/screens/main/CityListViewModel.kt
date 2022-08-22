package com.example.favtownlists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _customCityListSF: MutableSharedFlow<CustomCityListModel> = MutableSharedFlow(1)
    val customCityListSF: SharedFlow<CustomCityListModel> = _customCityListSF.asSharedFlow()

    private val _customCityListsSF: MutableSharedFlow<List<CustomCityListModel>> = MutableSharedFlow(1)
    val customCityListsSF: SharedFlow<List<CustomCityListModel>> = _customCityListsSF.asSharedFlow()

    fun getCustomCityListById(id: Int) {
        getCustomCityListJob?.cancel()
        getCustomCityListJob = viewModelScope.launch {
            _customCityListSF.emitAll(
                useCase.getCustomCityListUseCase(id)
            )
        }
    }

    fun getCustomCityLists() {
        getCustomCityListJob?.cancel()
        getCustomCityListJob = viewModelScope.launch {
            _customCityListsSF.emitAll(
                useCase.getCustomCityListsUseCase()
            )
        }
    }
}