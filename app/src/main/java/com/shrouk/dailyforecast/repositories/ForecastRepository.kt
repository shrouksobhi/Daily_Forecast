package com.shrouk.dailyforecast.repositories

import androidx.lifecycle.LiveData
import com.shrouk.dailyforecast.model.Resource
import com.shrouk.dailyforecast.model.WeatherCity
import com.shrouk.dailyforecast.model.WeatherList
import com.shrouk.dailyforecast.model.WeatherResponse

interface ForecastRepository {

    suspend fun getCityForecast(city:String): Resource<ArrayList<WeatherList>, WeatherCity>

    suspend fun getSavedForecast(cityName:String): LiveData<WeatherResponse>

    suspend fun saveForecast(weatherResponse: WeatherResponse)
}