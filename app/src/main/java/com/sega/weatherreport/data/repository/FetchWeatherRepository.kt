package com.sega.weatherreport.data.repository


import com.sega.weatherreport.data.local.WeatherDatabase
import com.sega.weatherreport.data.local.mapper.toListOfWeatherInfo
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
    suspend fun getWeather(): Flow<Resource<List<WeatherInfo>>> {
        return flow {
            val totalSeconds = 60
            val localWeatherInfos = dao.getAll()
            val isDbEmpty = localWeatherInfos.isEmpty()
            Timber.i("Db action isDbEmpty $isDbEmpty size is ${localWeatherInfos.size}")
            if (!isDbEmpty) {
                dao.deleteAll()
            }
            for (i in 0..totalSeconds step 10) {
                when (i) {
                    0 -> {
                        Timber.i("counter tick is $i and city is Paris")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        fetchInfos("Paris")
                    }
                    10 -> {

                        Timber.i("counter tick is $i and city is Rennes")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        fetchInfos("Rennes")

                    }
                    20 -> {

                        Timber.i("counter tick is $i and city is Nantes")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        fetchInfos("Nantes")
                    }
                    30 -> {
                        Timber.i("counter tick is $i and city is Bordeaux")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        fetchInfos("Bordeaux")

                    }
                    40 -> {
                        Timber.i("counter tick is $i and city is Lyon")
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        fetchInfos("Lyon")

                    }
                    50 -> {
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))

                    }
                    60 -> {
                        val localWeatherList = dao.getAll()
                        val progress = getProgresspercentage(i, totalSeconds)
                        emit(Resource.Progress(progress))
                        localWeatherList?.let {
                            emit(Resource.Success(localWeatherInfos.toListOfWeatherInfo()))
                        }

                    }

                }
                kotlinx.coroutines.delay(10000)

            }

        }

    }

    private suspend fun fetchInfos(cityName: String) {
        Timber.i("Db action fetchInfos called")
        withContext(Dispatchers.IO) {
            try {
                Timber.i("Db action fetchInfos called $cityName")
                val weatherInfoDto =
                    retrofitService.getWeatherInfos(cityName)
                Timber.i("Db action start inserting weather")
                val weathetInfoEntity = weatherInfoDto.toWeatherInfoEntity()
                dao.insertWeatherInfos(weathetInfoEntity)
                Timber.i("Db action end inserting weather")
                val weathetInfo = weatherInfoDto.toWeatherInfo()


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



