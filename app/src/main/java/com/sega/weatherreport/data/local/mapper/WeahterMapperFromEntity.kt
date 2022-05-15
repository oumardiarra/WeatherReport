package com.sega.weatherreport.data.local.mapper

import com.sega.weatherreport.data.local.model.*
import com.sega.weatherreport.data.remote.dto.*
import com.sega.weatherreport.domain.model.*

/* mappers from entities to domain */
fun WeatherInfoEntity.toWeatherInfo(): WeatherInfo {
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

fun CoordEntity.toCoord(): Coord {
    return Coord(lon = lon, lat = lat)
}

fun CloudsEntity.toClouds(): Clouds {
    return Clouds(all = all)
}

fun SysEntity.toSys(): Sys {
    return Sys(type = type, id = id, country = country, sunrise = sunrise, sunset = sunset)
}

fun MainEntity.toMain(): Main {
    return Main(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity
    )
}

fun WindEntity.toWind(): Wind {
    return Wind(speed = speed, deg = deg)
}

fun List<WeatherEntity>.toListOfWeather(): List<Weather> {
    return map {
        Weather(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
}

fun List<WeatherInfoEntity>.toListOfWeatherInfo(): List<WeatherInfo> {
    return map {
        WeatherInfo(
            coord = it.coord.toCoord(),
            weather = it.weather.toListOfWeather(),
            base = it.base,
            id = it.id,
            main = it.main.toMain(),
            visibility = it.visibility,
            wind = it.wind.toWind(),
            clouds = it.clouds.toClouds(),
            dt = it.dt,
            sys = it.sys.toSys(),
            timezone = it.timezone,
            name = it.name,
            cod = it.cod
        )
    }
}