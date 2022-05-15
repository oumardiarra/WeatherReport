package com.sega.weatherreport.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity
data class WeatherInfoEntity(
    @Embedded val coord: CoordEntity,
    val weather: List<WeatherEntity>,
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


)

class Converters {

    @TypeConverter
    fun listToJson(value: List<WeatherEntity>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<WeatherEntity>? {

        val objects =
            Gson().fromJson(value, Array<WeatherEntity>::class.java) as Array<WeatherEntity>
        return objects.toList()
    }
}

