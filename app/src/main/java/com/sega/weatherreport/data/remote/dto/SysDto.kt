package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class SysDto(
    @Json(name = "type")
    val type: Long,
    @Json(name = "id")
    val id: Long,
    @Json(name = "country")
    val country: String,
    @Json(name = "sunrise")
    val sunrise: Long,
    @Json(name = "sunset")
    val sunset: Long
)
