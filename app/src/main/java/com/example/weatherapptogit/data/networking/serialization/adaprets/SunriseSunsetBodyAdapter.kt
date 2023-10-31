package com.example.weatherapptogit.data.networking.serialization.adaprets

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.weatherapptogit.data.networking.serialization.responses.SunriseSunsetBody
import com.example.weatherapptogit.domain.models.SunriseSunset
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class SunriseSunsetBodyAdapter : JsonDeserializer<SunriseSunset> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SunriseSunset {
        val body = context?.deserialize<SunriseSunsetBody>(json, SunriseSunsetBody::class.java)
        val ldtSunrise = LocalDateTime.ofInstant(Instant.ofEpochSecond(body?.sunrise ?: 0), ZoneId.systemDefault())

        val ldtSunset = LocalDateTime.ofInstant(Instant.ofEpochSecond(body?.sunset ?: 0), ZoneId.systemDefault())

        return SunriseSunset(
            sunrise = ldtSunrise,
            sunset = ldtSunset
        )
    }
}