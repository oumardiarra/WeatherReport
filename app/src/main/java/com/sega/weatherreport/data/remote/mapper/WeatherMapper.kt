package com.sega.weatherreport.data.remote.mapper

import com.sega.weatherreport.data.remote.dto.*
import com.sega.weatherreport.domain.model.*


fun WeatherInfoDto.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        coord = coord.toCoord(),
        weather = weather.toListOfWeather(),
        base = base,
        main = main.toMain(),
        visibility = visibility,
        wind = wind.toWind(),
        clouds = clouds.toClouds(),
        dt = dt,
        sys = sys.toSys(),
        timezone = timezone,
        id = id,
        name = name,
        cod = cod
    )
}

fun CoordDto.toCoord(): Coord {
    return Coord(lon = lon, lat = lat)
}

fun CloudsDto.toClouds(): Clouds {
    return Clouds(all = all)
}

fun SysDto.toSys(): Sys {
    return Sys(type = type, id = id, country = country, sunrise = sunrise, sunset = sunset)
}

fun MainDto.toMain(): Main {
    return Main(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity
    )
}

fun WindDto.toWind(): Wind {
    return Wind(speed = speed, deg = deg)
}

fun List<WeatherDto>.toListOfWeather(): List<Weather> {
    return map {
        Weather(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
}