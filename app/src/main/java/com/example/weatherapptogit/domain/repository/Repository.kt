package com.example.weatherapptogit.domain.repository

import com.example.weatherapptogit.core.ResultWrapper
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.domain.models.AirQuality
import com.example.weatherapptogit.domain.models.Weather

interface Repository {

    suspend fun getWeather(): ResultWrapper<Pair<Weather, List<Weather>>>

    fun saveCity(city : Cities)
    fun getCity(): Cities

}