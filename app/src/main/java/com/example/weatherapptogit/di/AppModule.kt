package com.example.weatherapptogit.di

import android.app.Application
import com.example.weatherapptogit.App
import com.example.weatherapptogit.data.networking.api.AirQualityApi
import com.example.weatherapptogit.data.networking.api.WeatherApi
import com.example.weatherapptogit.data.repository.RepositoryImpl
import com.example.weatherapptogit.data.storage.sharedPreferences.Settings
import com.example.weatherapptogit.data.storage.sharedPreferences.SettingsImpl
import com.example.weatherapptogit.domain.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val applicationContext : App) {

    @Provides
    fun provideSettings() : Settings{
        return SettingsImpl(applicationContext)
    }

    @Provides
    fun provideRepository(weatherApi: WeatherApi, airQualityApi: AirQualityApi, settings : Settings): Repository {
        return RepositoryImpl(weatherApi,airQualityApi,settings)
    }

}