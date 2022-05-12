package com.sega.weatherreport.data.repository

import com.sega.weatherreport.data.remote.api.WeatherApi
import com.sega.weatherreport.data.remote.api.WeatherApiService
import com.sega.weatherreport.data.remote.mapper.toWeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


class FetchWeatherRepository {

    suspend fun getWeather() {
        withContext(Dispatchers.IO) {
            try {
                Timber.i("Starting calling the service")
                val weatherInfoDto =
                    WeatherApi.retrofitService.getWeatherInfos("Paris")
                val weathetInfo = weatherInfoDto.toWeatherInfo()
                Timber.i("Weather infos: Name ${weathetInfo.name}, temp:${weathetInfo.main.temp}")
            } catch (ex: HttpException) {
                ex.printStackTrace()
                Timber.e("An error occured during the fecth operation: ${ex.message()}")
            } catch (ioEx: IOException) {
                ioEx.printStackTrace()
                Timber.e("An error occured during the fecth operation: ${ioEx.message}")
            } catch (genEx: Exception) {
                genEx.printStackTrace()
                Timber.e("An error occured during the fecth operation: ${genEx.message}")
            }

        }
    }


}