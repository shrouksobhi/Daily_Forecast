package com.shrouk.dailyforecast.dataSource.remoteDataSource

import com.shrouk.dailyforecast.model.ServerResponse
import com.shrouk.dailyforecast.model.WeatherCity
import com.shrouk.dailyforecast.model.WeatherList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("forecast")
    suspend fun getCityForecast(
        @Query("appid") apiKey: String,
        @Query("q") city:String,
       @Query("units") units:String ="metric"

    ):Response<ServerResponse<ArrayList<WeatherList>,WeatherCity>>


}
