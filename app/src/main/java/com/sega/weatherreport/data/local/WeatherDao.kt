package com.sega.weatherreport.data.local

import androidx.room.*
import com.sega.weatherreport.data.local.model.WeatherInfoEntity
import com.sega.weatherreport.domain.model.WeatherInfo

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfos(weatherInfo: WeatherInfoEntity)

    @Query("select * from WeatherInfoEntity")
    suspend fun getAll(): List<WeatherInfoEntity>

    @Query("delete from WeatherInfoEntity")
    suspend fun deleteAll()
}