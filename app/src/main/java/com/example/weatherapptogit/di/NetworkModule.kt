package com.example.weatherapptogit.di

import com.example.weatherapptogit.data.networking.api.AirQualityApi
import com.example.weatherapptogit.data.networking.api.WeatherApi
import com.example.weatherapptogit.data.networking.serialization.adaprets.AirQualityBodyAdapter
import com.example.weatherapptogit.data.networking.serialization.adaprets.SunriseSunsetBodyAdapter
import com.example.weatherapptogit.data.networking.serialization.adaprets.WeatherBodyAdapter
import com.example.weatherapptogit.domain.models.AirQuality
import com.example.weatherapptogit.domain.models.SunriseSunset
import com.example.weatherapptogit.domain.models.Weather
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "http://api.openweathermap.org"

@Module
class NetWorkModule {

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Weather::class.java, WeatherBodyAdapter())
            .registerTypeAdapter(SunriseSunset::class.java, SunriseSunsetBodyAdapter())
            .registerTypeAdapter(AirQuality::class.java, AirQualityBodyAdapter())
            .setLenient()
            .create()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
    }

    @Provides
    fun provideApi (retrofit: Retrofit.Builder) : WeatherApi {
        return retrofit.baseUrl(BASE_URL).build().create(WeatherApi::class.java)
    }

    @Provides
    fun provideAirQualityApi (retrofit: Retrofit.Builder) : AirQualityApi {
        return retrofit.baseUrl(BASE_URL).build().create(AirQualityApi::class.java)
    }


}