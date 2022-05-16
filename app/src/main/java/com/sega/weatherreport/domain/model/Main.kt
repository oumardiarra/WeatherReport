package com.sega.weatherreport.domain.model

import com.squareup.moshi.Json

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)
