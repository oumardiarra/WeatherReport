package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class WeatherInfoDto(
    @field:Json(name = "name") val cityName: String,
    @Json(name = "main") val temperature: MainInfo,
    @Json(name = "weather") val weather: List<WeatherDetails>
)
