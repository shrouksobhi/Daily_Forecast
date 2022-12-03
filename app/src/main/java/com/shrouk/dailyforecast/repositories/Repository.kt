package com.shrouk.dailyforecast.repositories

import androidx.lifecycle.LiveData
import com.shrouk.dailyforecast.BuildConfig
import com.shrouk.dailyforecast.dataSource.localDataSource.DaoInterface

import com.shrouk.dailyforecast.dataSource.remoteDataSource.Api
import com.shrouk.dailyforecast.forecastExt.getResponse
import com.shrouk.dailyforecast.model.Resource
import com.shrouk.dailyforecast.model.WeatherCity
import com.shrouk.dailyforecast.model.WeatherList
import com.shrouk.dailyforecast.model.WeatherResponse

import javax.inject.Inject

class Repository @Inject constructor(private var apiInterface: Api,
                                     private val forecastDao : DaoInterface) : ForecastRepository
 {
    private val appid:String= BuildConfig.API_KEY
      //  "7b18784c8c23845ce398f81edce84764"
     override suspend fun getCityForecast(city:String): Resource<ArrayList<WeatherList>, WeatherCity> {
         return getResponse(
             {apiInterface.getCityForecast(appid,city)},
         0)
     }

     override suspend fun getSavedForecast(cityName:String): LiveData<WeatherResponse> {
        return forecastDao.showForecast(cityName)
    }
     override suspend fun saveForecast(weatherResponse: WeatherResponse){
        forecastDao.saveForecastData(weatherResponse)
    }


}