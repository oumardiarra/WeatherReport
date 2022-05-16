package com.sega.weatherreport.presentation.weather_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sega.weatherreport.data.repository.FetchWeatherRepository
import com.sega.weatherreport.domain.model.WeatherInfo
import com.sega.weatherreport.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FetchWeatherViewModel @Inject constructor(
    private val repository: FetchWeatherRepository
) : ViewModel() {

    private var _progressIndicator = MutableStateFlow(0)
    private var _waitingMessage = MutableStateFlow("")
    private var _listOfWeather = MutableStateFlow(emptyList<WeatherInfo>())
    val progressIndicator = _progressIndicator
    val waitingMessage = _waitingMessage
    val listOfWeather = _listOfWeather

    init {

        getWaintingMessage()
    }

    fun fetchCurrentWeather() {
        getWaintingMessage()
        viewModelScope.launch {
            repository.getWeather()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { weatherInfo ->
                                Timber.e("Resource.Success ${weatherInfo.isEmpty()} ")
                                _listOfWeather.value = weatherInfo

                            }
                        }
                        is Resource.Progress -> {
                            it.progress?.let { progress ->
                                _progressIndicator.value = progress

                            }
                        }
                        is Resource.Error -> {
                            /* We could update the UI with a corresponding message*/
                            Timber.e(it.message)
                        }
                    }

                }
        }
    }



    fun reset() {
        _progressIndicator.value = 0
        resetList()
    }

    fun resetList() {
        _listOfWeather.value = emptyList<WeatherInfo>()
    }

    fun getWaintingMessage() {
        viewModelScope.launch {
            repository
                .waitingMessage()
                .collect {
                    _waitingMessage.value = it
                }
        }
    }
}