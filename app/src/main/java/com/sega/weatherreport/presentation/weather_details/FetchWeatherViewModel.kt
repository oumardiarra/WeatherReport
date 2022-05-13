package com.sega.weatherreport.presentation.weather_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sega.weatherreport.data.repository.FetchWeatherRepository
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
): ViewModel() {

    private val _progressIndicator = MutableStateFlow(0)
    private val _waitingMessage = MutableStateFlow("")
    val progressIndicator = _progressIndicator
    val waitingMessage = _waitingMessage

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
                    _waitingMessage.value = it
                }
        }
    }
}