package com.shrouk.dailyforecast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Weather(
    var id: Long,
    var main: String,
    var description: String,
//    var icon:String
):Parcelable
