package com.example.favtownlists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.data.data_source.CustomCityList
import com.example.favtownlists.data.repository.CityListRepositoryImpl
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.use_case.UseCases
import com.example.favtownlists.screens.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val useCase: UseCases,
    private val router: Router,
    val repository: CityListRepositoryImpl
) : ViewModel() {

    private var getCustomCityListJob: Job? = null

    private val _customCityList: MutableStateFlow<CustomCityListModel?> = MutableStateFlow(null)
    val customCityList: StateFlow<CustomCityListModel?> = _customCityList.asStateFlow()

    fun routeToMyListsScreen() {
        router.navigateTo(Screens.MyListsScreen())
    }

    init {
        //getCustomCityListById(1)
    }

    fun getCustomCityListById(id: Int) {
        getCustomCityListJob?.cancel()
        viewModelScope.launch {
           _customCityList.value = useCase.getCustomCityListUseCase(id)
        }
    }
}