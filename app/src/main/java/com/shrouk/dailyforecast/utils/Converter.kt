package com.shrouk.dailyforecast.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shrouk.dailyforecast.model.*

class Converter {
    @TypeConverter
    fun weatherListToJson(value: List<WeatherList>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToWeatherList(value: String) = Gson().fromJson(value, Array<WeatherList>::class.java).toList()

    @TypeConverter
    fun weatherCityToJson(value: WeatherCity?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToWeatherCity(value: String) = Gson().fromJson(value,WeatherCity::class.java)

    @TypeConverter
    fun  coordToJson(value: Coord?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToCoord(value: String) = Gson().fromJson(value, Coord::class.java)

    @TypeConverter
    fun mainToJson(value: Main?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToMain(value: String) = Gson().fromJson(value, Main::class.java)

    @TypeConverter
    fun weatherToJson(value: List<Weather>?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToWeather(value: String) = Gson().fromJson(value,Array<Weather>::class.java).toList()

    @TypeConverter
    fun windToJson(value: Wind?) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonToWind(value: String) = Gson().fromJson(value, Wind::class.java)

}
