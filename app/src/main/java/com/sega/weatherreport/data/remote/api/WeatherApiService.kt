package com.sega.weatherreport.data.remote.api

import com.sega.weatherreport.BuildConfig
import com.sega.weatherreport.data.remote.dto.WeatherInfoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query





interface WeatherApiService {
    @GET("weather")
    suspend fun getWeatherInfos(
        @Query("q") cityName: String,
        @Query("APPID") apiKey: String = API_KEY
    ): WeatherInfoDto

companion object{
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = BuildConfig.WEATHER_API_KEY
}
}

