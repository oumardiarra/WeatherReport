package com.sega.weatherreport.domain.model

import com.squareup.moshi.Json

data class WeatherDetails(
     val description: String,
     val icon: String
)