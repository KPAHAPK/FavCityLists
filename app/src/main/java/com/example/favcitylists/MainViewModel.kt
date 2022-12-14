package com.example.favcitylists

import androidx.lifecycle.ViewModel
import com.example.favcitylists.screens.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    fun routeToCityListScreen() {
        router.newRootChain(Screens.CityListScreen())
    }

}