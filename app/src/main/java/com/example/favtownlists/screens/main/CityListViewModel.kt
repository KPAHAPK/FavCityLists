package com.example.favtownlists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.repository.room.CityListInfoRepository
import com.example.favtownlists.screens.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val repository: CityListInfoRepository
) : ViewModel() {
    @Inject
    lateinit var router: Router

    private var getCustomCityListJob: Job? = null

    fun routeToMyListsScreen() {
        router.navigateTo(Screens.MyListsScreen())
    }

    fun getCityList() {
        getCustomCityListJob?.cancel()
        getCustomCityListJob = repository.getAllList()
            .onEach { customCityList ->

            }.launchIn(viewModelScope)
    }

}