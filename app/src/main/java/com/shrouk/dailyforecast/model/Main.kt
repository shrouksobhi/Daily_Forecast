package com.shrouk.dailyforecast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Main (
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
  var temp_max: Double,
  var  pressure: Long,
 var  sea_level: Long,
 var  grnd_level: Long,
 var  humidity: Int,
var  temp_kf : Float
):Parcelable
