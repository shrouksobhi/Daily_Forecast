package com.shrouk.dailyforecast.model



data class Resource<out T,out Y>(val cod: Cod, val message:Int?=0,val cnt:Int?=0,val weatherlist: T?,val weathercity:Y?
) {

    companion object {

        fun <T,Y> success(weatherlist: T?,weathercity:Y?): Resource<T,Y> {
            return Resource(Cod.OK,0,0, weatherlist,weathercity )
        }
        fun <T,Y> error(message: Int?): Resource<T,Y> {
            return Resource(Cod.ERROR,message,0,null,null)
        }

        fun <T,Y> loading(message: Int?): Resource<T,Y> {
            return Resource(Cod.LOADING,message,0, null,null)
        }

    }

}