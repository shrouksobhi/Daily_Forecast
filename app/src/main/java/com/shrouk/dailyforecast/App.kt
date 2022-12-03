package com.shrouk.dailyforecast

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {
    override fun onCreate() {

        appContext= this.applicationContext
        super.onCreate()

    }
    companion object {
        lateinit var appContext: Context
     //   var instance: App? = App()


        fun applicationContext(): Context {
            return appContext
        }
    }
}