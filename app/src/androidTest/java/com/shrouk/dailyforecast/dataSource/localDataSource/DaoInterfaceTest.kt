package com.shrouk.dailyforecast.dataSource.localDataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.shrouk.dailyforecast.getOrAwaitValue
import com.shrouk.dailyforecast.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoInterfaceTest {
    @get:Rule
    var instancsTaskExecutorRule=InstantTaskExecutorRule()

    private lateinit var database:ForeCastDataBase
    private lateinit var dao:DaoInterface

    @Before
    fun setup(){
         database= Room.inMemoryDatabaseBuilder(
             ApplicationProvider.getApplicationContext(),
             ForeCastDataBase::class.java
         ).allowMainThreadQueries().build()
        dao=database.forecastDatabaseDao()
    }
    @After
    fun teardown(){
        database.close()
    }
    @Test
    fun saveForecatData()= runBlockingTest {
        val weatherList=listOf (
            WeatherList("2022-12-01",1,3.4,0,
                Clouds("cloud")
                , Main(19.6,8.7,0.0,0.0,
                    4,4,4,4,4f)
                ,listOf(Weather(22,",","clear")), Wind(4f,0,0f), Sys("")
            )
        )
        val weatherCity=WeatherCity(44,"cairo",Coord(88.0,99.0),"egypt",
            1000,0,0,0)

        val weatherResponse=WeatherResponse(weatherList, weatherCity)

       dao.saveForecastData(weatherResponse)
       val forecastData=  dao.showForecast(weatherCity.name).getOrAwaitValue()

        assertThat(forecastData).isEqualTo(weatherResponse)
    }
}