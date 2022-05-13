package com.sega.weatherreport.presentation.weather_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sega.weatherreport.data.repository.FetchWeatherRepository
import com.sega.weatherreport.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class FetchWeatherViewModel : ViewModel() {
    val repository = FetchWeatherRepository()
    private val _progressIndicator = MutableStateFlow(0)
    val progressIndicator = _progressIndicator

    init {
        getWaintingMessage()
    }

    fun fetchCurrentWeather() {

        viewModelScope.launch {
            repository.getWeather()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { weatherInfo ->
                                Timber.i("Weather collected from flow ${weatherInfo.main.temp}")
                            }
                        }
                        is Resource.Progress -> {
                            it.progress?.let { progress ->
                                _progressIndicator.value = progress
                                Timber.i("Weather collected from flow progress ${progress}")
                            }
                        }
                        is Resource.Error -> {

                        }
                    }

                }
        }
    }

    fun getWaintingMessage() {
        viewModelScope.launch {
            repository
                .waitingMessage()
                .collect {
                    Timber.i("Wainting message is ${it}")
                }
        }
    }
}