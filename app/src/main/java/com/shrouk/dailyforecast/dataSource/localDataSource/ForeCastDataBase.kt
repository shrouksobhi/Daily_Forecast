package com.shrouk.dailyforecast.dataSource.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shrouk.dailyforecast.utils.Converter
import com.shrouk.dailyforecast.model.WeatherResponse

@Database(entities =[WeatherResponse::class] , version = 5 ,exportSchema = false)
@TypeConverters(Converter::class)

abstract class ForeCastDataBase:RoomDatabase() {
    abstract fun forecastDatabaseDao(): DaoInterface

}