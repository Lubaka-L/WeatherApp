package com.example.weatherapptogit

import android.app.Application
import com.example.weatherapptogit.di.AppComponent
import com.example.weatherapptogit.di.AppModule
import com.example.weatherapptogit.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var component : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}