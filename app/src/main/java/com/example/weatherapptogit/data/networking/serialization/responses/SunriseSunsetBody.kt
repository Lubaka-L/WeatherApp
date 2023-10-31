package com.example.weatherapptogit.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class SunriseSunsetBody(

    @SerializedName("sunrise")
    val sunrise: Long?,

    @SerializedName("sunset")
    val sunset: Long?
)