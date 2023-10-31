package com.example.weatherapptogit.data.networking.api

import com.example.weatherapptogit.data.networking.serialization.responses.ListAndCityWrapper
import com.example.weatherapptogit.domain.models.SunriseSunset
import com.example.weatherapptogit.domain.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY = "6d60fea75691d8f787af5ae13e8507fd"

interface WeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "6d60fea75691d8f787af5ae13e8507fd",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Response<ListAndCityWrapper<List<Weather>, SunriseSunset>>

}