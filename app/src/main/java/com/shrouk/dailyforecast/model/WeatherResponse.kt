package com.shrouk.dailyforecast.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity(tableName = "forecast_Table")
data class WeatherResponse(


   // var id:Long?=0,

    @ColumnInfo(name ="weather")
    var weatherList: List<WeatherList> = listOf (WeatherList("",0,0.0,0, Clouds("")
        ,Main(0.0,0.0,0.0,0.0,0,0,0,0,0f)
        ,listOf(Weather(0,",","")), Wind(0f,0,0f), Sys("")
    )) ,
    @PrimaryKey
        @ColumnInfo(name ="city")
        var weatherCity: WeatherCity=WeatherCity(0,"",Coord(0.0,0.0),"",0,0,0,0)

//    @SerializedName("dt_txt")
//    @ColumnInfo(name ="dt_txt")
//    var date :String="",
//    @ColumnInfo(name ="main")
//    var main:Main= Main(0.0,0.0,0.0,0.0,0,0,0,0,0f),
//    @ColumnInfo(name ="weather")
//    var weather:List<Weather> = listOf(Weather(0,",","")),
//    @ColumnInfo(name ="wind")
//    var wind:Wind=Wind(0f,0,0f),

    ):Parcelable