package com.example.weatherapptogit.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class ListAndCityWrapper<T, R>(
    @SerializedName("list")
    val list: T,

    @SerializedName("city")
    val city: R
)

data class ListWrapper<T>(
    @SerializedName("list")
    val list: T
)