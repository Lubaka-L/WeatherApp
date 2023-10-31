package com.example.weatherapptogit.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapptogit.App
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SearchViewModel : ViewModel() {

    private val _citiesList: MutableStateFlow<List<Cities>> = MutableStateFlow(listOf())
    val citiesList: StateFlow<List<Cities>> = _citiesList.asStateFlow()

    private val cityNameFromEnum = mutableListOf<Cities>()

    @Inject
    lateinit var repository: Repository

    init {
        App.component.inject(this)

        val savedCity = repository.getCity()

        Cities.values().forEach {
            cityNameFromEnum.add(it)
        }
        _citiesList.update {
            cityNameFromEnum
        }
    }

    fun showCitiesNames(query: String) {
        _citiesList.update {
            cityNameFromEnum.filter {
                it.city.lowercase().contains(query.lowercase())
            }
        }
    }

    fun saveCity(cities: Cities){
        repository.saveCity(cities)
    }
}