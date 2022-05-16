package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class CloudsDto(
    @Json(name = "all")val all: Long)
