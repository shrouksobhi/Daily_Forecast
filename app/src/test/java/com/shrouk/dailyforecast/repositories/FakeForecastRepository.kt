package com.shrouk.dailyforecast.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shrouk.dailyforecast.model.*

class FakeForecastRepository : ForecastRepository {

    private val roomWeatherResponse= mutableListOf(WeatherResponse(listOf(WeatherList()),WeatherCity()))
    private val apiWeatherResponse= (WeatherResponse(listOf(WeatherList()),WeatherCity()))

    private var observableSavedForecast=MutableLiveData(apiWeatherResponse)
    private var shouldReturnError=false

    fun setShouldReturnError(value:Boolean){
        shouldReturnError=value
    }

    override suspend fun getCityForecast(city: String): Resource<ArrayList<WeatherList>, WeatherCity> {
       if (shouldReturnError){
            return Resource.error(400)
        }
            return Resource.success(java.util.ArrayList<WeatherList>(), WeatherCity())
    }
    private fun refreshLiveData() {
        for (weather in roomWeatherResponse) {
            observableSavedForecast.postValue(weather)

        }
    }
    override suspend fun getSavedForecast(cityName: String): LiveData<WeatherResponse> {
     return observableSavedForecast

    }

    override suspend fun saveForecast(weatherResponse: WeatherResponse) {
        roomWeatherResponse.add(weatherResponse)
        refreshLiveData()
    }
}