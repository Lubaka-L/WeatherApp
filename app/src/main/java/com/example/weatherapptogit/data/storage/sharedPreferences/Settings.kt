package com.example.weatherapptogit.data.storage.sharedPreferences

import com.example.weatherapptogit.data.enums.Cities

interface Settings {

    fun putCity(city: Cities)
    fun getCity(): Cities

}