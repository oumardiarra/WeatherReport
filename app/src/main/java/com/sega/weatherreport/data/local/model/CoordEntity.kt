package com.sega.weatherreport.data.local.model

import androidx.room.Entity
import com.squareup.moshi.Json
@Entity
data class CoordEntity(
    val lon: Double,
    val lat: Double
)
