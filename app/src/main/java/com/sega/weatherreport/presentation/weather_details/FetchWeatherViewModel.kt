package com.sega.weatherreport.presentation.weather_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sega.weatherreport.data.repository.FetchWeatherRepository
import kotlinx.coroutines.launch

class FetchWeatherViewModel : ViewModel() {
    val repository = FetchWeatherRepository()

    init {

    }

    fun fectDate(){
        viewModelScope.launch {
            repository.getWeather()
        }
    }
}