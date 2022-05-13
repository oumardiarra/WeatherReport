package com.sega.weatherreport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sega.weatherreport.data.local.model.WeatherInfoEntity

@Database(
    entities = [WeatherInfoEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao
}