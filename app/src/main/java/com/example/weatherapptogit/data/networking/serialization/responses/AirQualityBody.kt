package com.example.weatherapptogit.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class AirQualityBody(
    @SerializedName("main")
    val main: Main?
) {
    data class Main(
        @SerializedName("aqi")
        val aqi: Int?
    )
}
