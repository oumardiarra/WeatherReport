package com.sega.weatherreport.data.remote.dto

import com.squareup.moshi.Json

data class WeatherInfoDto(
    @Json(name = "coord") val coord: CoordDto,
    @Json(name = "weather") val weather: List<WeatherDto>,
    @Json(name = "base")val base: String,
    @Json(name = "main")val main: MainDto,
    @Json(name = "visibility")val visibility: Long,
    @Json(name = "wind") val wind: WindDto,
    @Json(name = "clouds") val clouds: CloudsDto,
    @Json(name = "dt") val dt: Long,
    @Json(name = "sys")val sys: SysDto,
    @Json(name = "timezone")val timezone: Long,
    @Json(name = "id")val id: Long,
    @Json(name = "name")val name: String,
    @Json(name = "cod") val cod: Long

)
