package com.shrouk.dailyforecast.model

import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
//@Entity(tableName = "forecast_Table")

data class WeatherList (
//    @PrimaryKey(autoGenerate = true)
//    var id:Long=0,

   @SerializedName("dt_txt")
//   @ColumnInfo(name ="dt_txt")
    var date :String="",
  // @Ignore
 //   @ColumnInfo(name ="visibility")
    var visibility:Long=0,
   // @ColumnInfo(name ="pop")
//   @Ignore
    var pop: Double=0.0,
  //  @ColumnInfo(name ="dt")
 //  @Ignore
    var dt: Long=0,
  //  @ColumnInfo(name ="clouds")
 // @Ignore
    var clouds:Clouds=Clouds(""),
  // @ColumnInfo(name ="main")
    var main:Main= Main(0.0,0.0,0.0,0.0,0,0,0,0,0f),
  //@ColumnInfo(name ="weather")
    var weather:List<Weather> = listOf(Weather(0,",","")),
 //  @ColumnInfo(name ="wind")
    var wind:Wind=Wind(0f,0,0f),
 //   @ColumnInfo(name ="sys")
  //  @Ignore
    var sys:Sys=Sys(""),

    ): Parcelable
@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Sys (
   var pod: String=""
        ):Parcelable
@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Wind (
   var speed: Float,
   var deg: Long,
var gust: Float
        ):Parcelable
@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Clouds (
        var all: String
):Parcelable