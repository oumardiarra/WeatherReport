package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class MainDto(
    @Json(name = "temp")
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "temp_min")
    val tempMin: Double,
    @Json(name = "temp_max")
    val tempMax: Double,
    @Json(name = "pressure")
    val pressure: Double,
    @Json(name = "humidity")
    val humidity: Double
)
