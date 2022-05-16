package com.sega.weatherreport.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sega.weatherreport.data.local.model.Converters
import com.sega.weatherreport.data.local.model.WeatherInfoEntity

@Database(
    entities = [WeatherInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao
}