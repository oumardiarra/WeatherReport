package com.sega.weatherreport.data.remote.api

import com.sega.weatherreport.BuildConfig
import com.sega.weatherreport.data.remote.dto.WeatherInfoDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val API_KEY = BuildConfig.WEATHER_API_KEY


private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()
val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeatherInfos(
        @Query("q") cityName: String,
        @Query("APPID") apiKey: String = API_KEY
    ): WeatherInfoDto


}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}