package com.example.weatherapptogit.di

import com.example.weatherapptogit.ui.mainWeather.main.MainWeatherViewModel
import com.example.weatherapptogit.ui.search.SearchViewModel
import dagger.Component

@Component(modules = [AppModule::class, NetWorkModule::class])
interface AppComponent {

    fun inject(mainWeatherViewModel: MainWeatherViewModel)
    fun inject(searchViewModel: SearchViewModel)

}