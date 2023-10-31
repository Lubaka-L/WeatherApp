package com.example.weatherapptogit.data.repository

import android.util.Log
import com.example.weatherapptogit.core.ResultWrapper
import com.example.weatherapptogit.core.ResultWrapper.Companion.success
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.data.networking.api.AirQualityApi
import com.example.weatherapptogit.data.networking.api.WeatherApi
import com.example.weatherapptogit.data.networking.serialization.responses.ListAndCityWrapper
import com.example.weatherapptogit.data.storage.sharedPreferences.Settings
import com.example.weatherapptogit.domain.models.AirQuality
import com.example.weatherapptogit.domain.models.SunriseSunset
import com.example.weatherapptogit.domain.models.Weather
import com.example.weatherapptogit.domain.repository.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val airQualityApi: AirQualityApi,
    private val settings: Settings
) : Repository {

    override suspend fun getWeather(): ResultWrapper<Pair<Weather, List<Weather>>> {
        return withContext(IO) {
            try {
                val weatherRes: ListAndCityWrapper<List<Weather>, SunriseSunset>? =
                    weatherApi.getWeather(settings.getCity().lat, settings.getCity().lon).body()
                val airQuality: Int? =
                    airQualityApi.getAirPollution(settings.getCity().lat, settings.getCity().lon)
                        .body()?.list?.firstOrNull()?.air_quality
                val firstWeather = weatherRes?.list?.first()!!
                val current = firstWeather.copy(
                    sunrise = weatherRes.city.sunrise,
                    sunset = weatherRes.city.sunset,
                    air_quality = airQuality ?: 0
                )
                success(current to weatherRes.list)
            } catch (e: Exception) {
                error(e)
            }

        }
    }

    override fun saveCity(city: Cities) {
        settings.putCity(city)
    }

    override fun getCity(): Cities {
        return settings.getCity()
    }

}