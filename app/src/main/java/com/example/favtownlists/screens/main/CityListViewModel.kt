package com.example.favtownlists.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.repository.room.CustomCityListRepository
import com.example.favtownlists.screens.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val repository: CustomCityListRepository
) : ViewModel() {
    @Inject
    lateinit var router: Router

    fun routeToMyListsScreen() {
        router.navigateTo(Screens.MyListsScreen())
    }

    fun getCityList() {
        viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch {
            repository.getAllList()
        }
    }

}