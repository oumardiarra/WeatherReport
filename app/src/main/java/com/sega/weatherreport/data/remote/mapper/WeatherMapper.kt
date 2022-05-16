package com.sega.weatherreport.data.remote.mapper

import com.sega.weatherreport.data.local.model.*
import com.sega.weatherreport.data.remote.dto.*
import com.sega.weatherreport.domain.model.*


/* mappers from dto to entities */
fun WeatherInfoDto.toWeatherInfoEntity(): WeatherInfoEntity {
    return WeatherInfoEntity(
        coord = coord.toCoordEntity(),
        weather = weather.toListOfWeatherEntity(),
        base = base,
        main = main.toMainEntity(),
        visibility = visibility,
        wind = wind.toWindEntity(),
        clouds = clouds.toCloudsEntity(),
        dt = dt,
        sys = sys.toSysEntity(),
        timezone = timezone,
        id = id,
        name = name,
        cod = cod
    )
}

fun CoordDto.toCoordEntity(): CoordEntity {
    return CoordEntity(lon = lon, lat = lat)
}

fun CloudsDto.toCloudsEntity(): CloudsEntity {
    return CloudsEntity(all = all)
}

fun SysDto.toSysEntity(): SysEntity {
    return SysEntity(type = type, id = id, country = country, sunrise = sunrise, sunset = sunset)
}

fun MainDto.toMainEntity(): MainEntity {
    return MainEntity(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity
    )
}

fun WindDto.toWindEntity(): WindEntity {
    return WindEntity(speed = speed, deg = deg)
}

fun List<WeatherDto>.toListOfWeatherEntity(): List<WeatherEntity> {
    return map {
        WeatherEntity(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
}