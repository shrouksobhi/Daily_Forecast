package com.shrouk.dailyforecast.model

data class ServerResponse<T, Y>(
    val cod: String, val message: Int, val cnt: Int, val list: T?, val city: Y?
)