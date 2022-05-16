package com.sega.weatherreport.domain.model

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    var icon: String
)
