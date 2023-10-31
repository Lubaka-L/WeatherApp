package com.example.weatherapptogit.data.storage.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapptogit.data.enums.Cities


class SettingsImpl(context: Context) : Settings {
    private val keyName = "keyName"

    private val preferences: SharedPreferences =
        context.getSharedPreferences("PrefTable", Context.MODE_PRIVATE)
    
    override fun putCity(city: Cities) {
        preferences.edit().apply {
            putString(keyName, city.name)
        }.apply()
    }

    override fun getCity(): Cities {
        val name = preferences.getString(keyName, "")
        return if (name.isNullOrBlank()){
            Cities.DEFAULT
        } else Cities.valueOf(name)
    }

}