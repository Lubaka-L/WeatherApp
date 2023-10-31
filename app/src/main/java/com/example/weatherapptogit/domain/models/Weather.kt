package com.example.weatherapptogit.domain.models

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.LocalDateTime

data class Weather(
    val data: String,
    val weather_description: String,
    val temp: Int,
    val feels_like: Int,
    val temp_min: Int,
    val temp_max: Int,
    val pressure: Int,
    val humidity: Int,
    val wind_speed: Int,
    val wind_deg: Int,
    val cod_id: Int,
    val clouds: Int,
    val visibility: Int,
    @DrawableRes val image: Int,
    val sunrise: LocalDateTime? = null,
    val sunset: LocalDateTime? = null,
    val air_quality: Int = 0

)