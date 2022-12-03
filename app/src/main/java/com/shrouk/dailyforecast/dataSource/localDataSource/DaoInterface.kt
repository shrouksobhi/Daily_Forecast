package com.shrouk.dailyforecast.dataSource.localDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shrouk.dailyforecast.model.WeatherResponse

@Dao
interface DaoInterface{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveForecastData(weatherResponse: WeatherResponse)
    @Query("SELECT * FROM forecast_table WHERE city LIKE '%' ||:cityName||'%'")
    fun showForecast(cityName:String):LiveData<WeatherResponse>
}