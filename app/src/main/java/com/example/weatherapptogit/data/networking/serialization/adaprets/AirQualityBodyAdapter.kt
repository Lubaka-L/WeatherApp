package com.example.weatherapptogit.data.networking.serialization.adaprets

import com.example.weatherapptogit.data.networking.serialization.responses.AirQualityBody
import com.example.weatherapptogit.domain.models.AirQuality
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AirQualityBodyAdapter : JsonDeserializer<AirQuality> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AirQuality {
        val body = context?.deserialize<AirQualityBody>(json, AirQualityBody::class.java)
        return AirQuality(
            air_quality = body?.main?.aqi ?: 1
        )
    }
}