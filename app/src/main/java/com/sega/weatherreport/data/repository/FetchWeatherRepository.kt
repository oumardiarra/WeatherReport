package com.sega.weatherreport.data.repository


import com.sega.weatherreport.data.local.WeatherDatabase
import com.sega.weatherreport.data.remote.api.WeatherApiService
import com.sega.weatherreport.data.remote.mapper.toWeatherInfo
import com.sega.weatherreport.data.remote.mapper.toWeatherInfoEntity
import com.sega.weatherreport.domain.model.WeatherInfo
import com.sega.weatherreport.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class FetchWeatherRepository @Inject constructor(
    private val retrofitService: WeatherApiService,
    private val waitingMessageList: MutableList<String>,
    private val db: WeatherDatabase
) {
    private val dao = db.dao
    suspend fun getWeather(): Flow<Resource<WeatherInfo>> {
        return flow {
            val totalSeconds = 60
            val localWeatherInfos = dao.getAll()
            val isDbEmpty = localWeatherInfos.isEmpty()
            Timber.i("Db action isDbEmpty $isDbEmpty")
            if (!isDbEmpty) {
                dao.deleteAll()
            }
            for (i in 0..totalSeconds step 10) {
                when (i) {
                    10 -> {
                        Timber.i("counter tick is $i and city is paris")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        val parisInfo = fetchInfos("Paris")
                        emitAll(parisInfo)
                    }
                    20 -> {
                        Timber.i("counter tick is $i and city is Rennes")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        val rennesInfos = fetchInfos("Rennes")
                        emitAll(rennesInfos)
                    }
                    30 -> {
                        Timber.i("counter tick is $i and city is Nantes")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        val nantesInfo = fetchInfos("Nantes")
                        emitAll(nantesInfo)
                    }
                    40 -> {
                        Timber.i("counter tick is $i and city is Bordeaux")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        val bordeauxInfos = fetchInfos("Bordeaux")
                        emitAll(bordeauxInfos)
                    }
                    50 -> {
                        Timber.i("counter tick is $i and city is Lyon")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        val lyonInfos = fetchInfos("Lyon")
                        emitAll(lyonInfos)
                    }

                }
                kotlinx.coroutines.delay(10000)

            }

        }

    }

    private suspend fun fetchInfos(cityName: String): Flow<Resource<WeatherInfo>> {
        return flow {
            try {
                val weatherInfoDto =
                    retrofitService.getWeatherInfos(cityName)
                Timber.i("Db action start inserting weather")
                val weathetInfoEntity = weatherInfoDto.toWeatherInfoEntity()
                dao.insertWeatherInfos(weathetInfoEntity)
                Timber.i("Db action end inserting weather")
                val weathetInfo = weatherInfoDto.toWeatherInfo()
                emit(Resource.Success(weathetInfo))

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

    suspend fun waitingMessage(): Flow<String> {
        var previousMessageDisplayed = ""
        return flow {
            while (true) {
                kotlinx.coroutines.delay(6000)
                val shuffledList = waitingMessageList.shuffled()
                if (previousMessageDisplayed.isEmpty()) {
                    previousMessageDisplayed = shuffledList.first()
                    emit(previousMessageDisplayed)
                } else {
                    val secondList =
                        shuffledList.filterNot { value -> value.equals(previousMessageDisplayed) }
                    previousMessageDisplayed = secondList.first()
                    emit(previousMessageDisplayed)
                }

            }
        }

    }

    private fun getProgresspercentage(currentSecond: Int, totalSeconds: Int): Int {
        val percentage = (currentSecond.toDouble() / totalSeconds) * 100
        return percentage.toInt()
    }

}



