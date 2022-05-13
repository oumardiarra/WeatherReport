package com.sega.weatherreport.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class WeatherEntity(
    @ColumnInfo(name = "Weather_id")
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)
