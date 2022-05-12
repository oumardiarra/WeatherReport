package com.sega.weatherreport.domain.model

import com.squareup.moshi.Json

data class WeatherInfo(
   val cityName: String,
    val temperature: MainInfo,
    val weather: List<WeatherDetails>
)
