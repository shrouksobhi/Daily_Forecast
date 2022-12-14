package com.shrouk.dailyforecast.dataSource.localDataSource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.shrouk.dailyforecast.getOrAwaitValue
import com.shrouk.dailyforecast.model.*
import com.shrouk.dailyforecast.ui.WeatherForecastFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class DaoInterfaceTest {

    @get:Rule
    var hiltRule=HiltAndroidRule(this)

    @get:Rule
    var instancsTaskExecutorRule=InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database:ForeCastDataBase
    private lateinit var dao:DaoInterface

    @Before
    fun setup(){
        hiltRule.inject()
        dao=database.forecastDatabaseDao()
    }
    @After
    fun teardown(){
        database.close()
    }
    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<WeatherForecastFragment> {

        }
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