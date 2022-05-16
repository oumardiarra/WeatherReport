package com.sega.weatherreport.data.local.model

import androidx.room.Entity

@Entity
data class WindEntity(
    val speed: Double,
    val deg: Long
)
