package com.example.weatherapptogit.ui.mainWeather.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapptogit.App
import com.example.weatherapptogit.core.ResultWrapper
import com.example.weatherapptogit.core.ResultWrapper.Companion.loading
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.domain.models.AirQuality
import com.example.weatherapptogit.domain.models.Weather
import com.example.weatherapptogit.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainWeatherViewModel : ViewModel() {

    private val _weather: MutableStateFlow<ResultWrapper<Pair<Weather, List<Weather>>>> = MutableStateFlow(loading())
    val weather: StateFlow<ResultWrapper<Pair<Weather, List<Weather>>>> = _weather.asStateFlow()

    private val _currentCity: MutableStateFlow<Cities?> = MutableStateFlow(null)
    val currentCity: StateFlow<Cities?> = _currentCity.asStateFlow()

    private val _airQuality: MutableStateFlow<Int?> = MutableStateFlow(0)
    val airQuality: StateFlow<Int?> = _airQuality.asStateFlow()

    @Inject
    lateinit var repository: Repository

    init {
        App.component.inject(this)

        _currentCity.update {
            repository.getCity()
        }

        loadWeather()

    }

    fun loadWeather() {
        viewModelScope.launch {
            _weather.update { repository.getWeather() }
        }
    }

    fun update(){
        _weather.update { loading() }
        loadWeather()
    }
}