package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class CoordDto(
    @Json(name = "lon")val lon: Double,
    @Json(name = "lat")val lat: Double
)
