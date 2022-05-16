package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "main")
    val main: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "icon")
    val icon: String
)
