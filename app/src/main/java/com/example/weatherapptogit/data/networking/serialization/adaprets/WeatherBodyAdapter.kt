package com.example.weatherapptogit.data.networking.serialization.adaprets

import com.example.weatherapptogit.R
import com.example.weatherapptogit.data.networking.serialization.responses.WeatherBody
import com.example.weatherapptogit.domain.models.Weather
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class WeatherBodyAdapter : JsonDeserializer<Weather> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): Weather {
        val body = context.deserialize<WeatherBody>(json, WeatherBody::class.java)
        return Weather(
            data = body?.dt_txt.orEmpty(),
            weather_description = body?.weather?.first()?.description.orEmpty(),
            temp = (body?.main?.temp ?: 0).toInt(),
            feels_like = (body?.main?.feels_like ?: 0).toInt(),
            temp_min = (body?.main?.temp_min ?: 0).toInt(),
            temp_max = (body?.main?.temp_max ?: 0).toInt(),
            pressure = (body?.main?.pressure ?: 0).toInt(),
            humidity = (body?.main?.humidity ?: 0).toInt(),
            wind_speed = (body?.wind?.speed ?: 0).toInt(),
            wind_deg = (body?.wind?.deg ?: 0).toInt(),
            cod_id = body?.weather?.first()?.id ?: 0,
            clouds = body?.clouds?.all ?: 0,
            visibility = (body?.visibility ?: 0).toInt(),
            image = when (body?.weather?.first()?.id ?: 0) {
                200, 201, 202 -> R.drawable.ic_cloud_bolt_rain
                210, 221 -> R.drawable.ic_cloud_sun_bolt
                211, 212 -> R.drawable.ic_cloud_bolt
                230, 231, 232 -> R.drawable.ic_cloud_bolt_rain

                300, 301, 310, 311 -> R.drawable.ic_cloud_drizzle
                302, 312 -> R.drawable.ic_cloud_rain
                313, 314, 321 -> R.drawable.ic_cloud_heavyrain

                500, 531 -> R.drawable.ic_cloud_drizzle
                511 -> R.drawable.ic_cloud_hail
                501, 520 -> R.drawable.ic_cloud_rain
                502, 503, 504, 521, 522 -> R.drawable.ic_cloud_heavyrain

                611, 612, 613, 615, 616 -> R.drawable.ic_cloud_sleet
                600, 602, 601, 620, 621, 622 -> R.drawable.ic_cloud_snow

                701, 711, 721, 741 -> R.drawable.ic_cloud_fog
                731, 751, 761, 762, 771, 781 -> R.drawable.ic_tornado

                800 -> R.drawable.ic_sun_max
                801, 802 -> R.drawable.ic_cloud_sun
                803 -> R.drawable.ic_cloud
                804 -> R.drawable.ic_smoke

                else -> R.drawable.ic_moon
            }
        )
    }
}