package com.example.favtownlists.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favtownlists.data.repository.CityListRepositoryImpl
import com.example.favtownlists.repository.room.model.CustomCityListModel
import com.example.favtownlists.repository.room.use_case.UseCases
import com.example.favtownlists.screens.Screens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val useCase: UseCases,
) : ViewModel() {

    private var getCustomCityListJob: Job? = null

    private val _customCityListLD: MutableLiveData<CustomCityListModel> =
        MutableLiveData<CustomCityListModel>()
    val customCityListLD: LiveData<CustomCityListModel>
        get() = _customCityListLD

    fun getCustomCityListById(id: Int) {
        getCustomCityListJob?.cancel()
        viewModelScope.launch {
            val customCityList = useCase.getCustomCityListUseCase(id)
            _customCityListLD.postValue(customCityList)
        }

    }
}