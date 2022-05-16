package com.sega.weatherreport.data.local.model

import androidx.room.Entity
import com.squareup.moshi.Json
@Entity
data class MainEntity(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)
