package com.sega.weatherreport.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherInfoEntity(
    @Embedded val coord: CoordEntity,
    @Embedded val weather: List<WeatherEntity>,
    val base: String,
    @Embedded val main: MainEntity,
    val visibility: Long,
    @Embedded val wind: WindEntity,
    @Embedded val clouds: CloudsEntity,
    val dt: Long,
    @Embedded val sys: SysEntity,
    val timezone: Long,
    @PrimaryKey val id: Long,
    val name: String,
    val cod: Long


){
    constructor():this(
        CoordEntity(0.0,0.0),
        listOf(),
        "",
        MainEntity(0.0, 0.0,0.0,0.0,0.0,0.0),
        0,
        WindEntity(0.0,0),
        CloudsEntity(0),
        0,
        SysEntity(0,0,"",0,0),
        0,
        0,
        "",
        0)



}
