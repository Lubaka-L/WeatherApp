package com.example.weatherapptogit.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class WeatherBody(

    @SerializedName("main")
    val main: Main?,

    @SerializedName("visibility")
    val visibility: Double?,

    @SerializedName("dt_txt")
    val dt_txt: String?,

    @SerializedName("weather")
    val weather: List<Weather?>,

    @SerializedName("clouds")
    val clouds: Clouds?,

    @SerializedName("wind")
    val wind: Wind?
) {
    data class Main(
        @SerializedName("aqi")
        val aqi: Int?,

        @SerializedName("temp")
        val temp: Double?,

        @SerializedName("feels_like")
        val feels_like: Double?,

        @SerializedName("temp_min")
        val temp_min: Double?,

        @SerializedName("temp_max")
        val temp_max: Double?,

        @SerializedName("pressure")
        val pressure: Double?,

        @SerializedName("humidity")
        val humidity: Double?
    )

    data class Weather(
        @SerializedName("id")
        val id: Int?,

        @SerializedName("description")
        val description: String?,
    )

    data class Clouds(
        @SerializedName("all")
        val all: Int?
    )

    data class Wind(
        @SerializedName("speed")
        val speed: Double?,

        @SerializedName("deg")
        val deg: Double?
    )
}
