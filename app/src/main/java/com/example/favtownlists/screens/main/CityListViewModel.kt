package com.example.favtownlists.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.repository.room.CityListInfoRepository
import com.example.favtownlists.repository.room.CityRepository
import com.example.favtownlists.repository.room.model.CityListInfoModel
import com.example.favtownlists.repository.room.model.CityModel
import com.example.favtownlists.screens.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val repository: CityRepository,
    private val repository2: CityListInfoRepository
) : ViewModel() {
    @Inject
    lateinit var router: Router

    private var getCustomCityListJob: Job? = null

    private val _allCities = MutableStateFlow(listOf<CityModel>())
    val allCities: StateFlow<List<CityModel>> = _allCities.asStateFlow()


    fun routeToMyListsScreen() {
        router.navigateTo(Screens.MyListsScreen())
    }

    fun setCityList() {
        getCustomCityListJob?.cancel()
        val cityList = mutableListOf<CityModel>()
        repository.getCities().stateIn(viewModelScope, SharingStarted.Lazily, listOf())
        _allCities.value = cityList
//        getCustomCityListJob = repository.getCities()
//            .onEach {
//
//            }
//            .launchIn(viewModelScope)
    }

    fun insert() {
        viewModelScope.launch {
            repository.insertCity(CityModel(null, "fsadf", "asdfa"))
            repository2.insert(CityListInfoModel(null, "ffff"))

        }
    }
}