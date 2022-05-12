package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDetails(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)