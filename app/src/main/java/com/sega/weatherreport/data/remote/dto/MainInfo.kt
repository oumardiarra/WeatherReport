package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class MainInfo(@Json(name = "temp")val temperatureInfo:Float)
