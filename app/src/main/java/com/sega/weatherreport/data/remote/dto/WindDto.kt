package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class WindDto(
    @Json(name = "speed")
    val speed: Double,
    @Json(name = "deg")
    val deg: Long
)
