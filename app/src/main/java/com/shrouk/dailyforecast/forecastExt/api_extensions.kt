package com.shrouk.dailyforecast.forecastExt

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shrouk.dailyforecast.model.Resource
import com.shrouk.dailyforecast.model.ServerResponse

import retrofit2.Response


suspend fun <T,Y> getResponse(
    request: suspend () -> Response<ServerResponse<T,Y>>,
    defaultErrorMessage: Int,
//: Resource<T>
): Resource<T, Y> {
    return try {
        val result = request.invoke()
        if (result.isSuccessful) {
            val response: ServerResponse<T, Y> = result.body()!!
            //  return Resource.success(response?.data, response?.message)
            return Resource.success(response.list,response.city)
        } else {
            val gson = Gson()
            val type = object : TypeToken<ServerResponse<T,Y>?>() {}.type
            Log.e("result code", result.code().toString())
            Log.e("result Error", result.errorBody()!!.charStream().readText())
            val errorResponse: ServerResponse<T,Y> =
                gson.fromJson(result.errorBody()!!.charStream(), type)
            Resource.error(errorResponse.message)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        Resource.error(defaultErrorMessage)
    }
}