@file:Suppress("DEPRECATION")

package com.shrouk.dailyforecast.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.shrouk.dailyforecast.MainCoroutineRule
import com.shrouk.dailyforecast.model.*
import com.shrouk.dailyforecast.repositories.FakeForecastRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@ExperimentalCoroutinesApi
class WeatherForecastViewModelTest{

    @get:Rule
    var instancsTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
  var mainCoroutineRule=MainCoroutineRule()
    private lateinit var viewModel: WeatherForecastViewModel
    private lateinit var repository: FakeForecastRepository


    @Before
    fun setup(){
        repository=FakeForecastRepository()

        viewModel=WeatherForecastViewModel(repository)
    }

    @Test
    fun `getForeCastDataFromRoomDB ,then check if input data equal to returned data`(){
        val weatherList=listOf (
            WeatherList("2022-12-01",1,3.4,0,
                Clouds("cloud")
                , Main(19.6,8.7,0.0,0.0,
                    4,4,4,4,4f)
                ,listOf(Weather(22,",","clear")), Wind(4f,0,0f), Sys("")
            )
        )
        val weatherCity=WeatherCity(44,"cairo", Coord(88.0,99.0),"egypt",
            1000,0,0,0)

        val weatherResponse=WeatherResponse(weatherList, weatherCity)

       // var weatherResponse=WeatherResponse((listOf(WeatherList(""))),WeatherCity(0))
        viewModel.saveForecast(weatherResponse)

        runBlockingTest {
            viewModel.city.addSource(repository.getSavedForecast(weatherCity.name)) {
             //   System.out.println("$it")
                viewModel.city.value = WeatherResponse(it.weatherList, it.weatherCity)
            }
        }
        val value= viewModel.city.getOrAwaitValueTest()
        assertThat(value.weatherCity.name).isEqualTo(weatherCity.name)
    }
    @Test
    fun `getForeCastDataFromApi,return ok`(){

        val weatherCity=WeatherCity(44,"cairo", Coord(88.0,99.0),"egypt",
            1000,0,0,0)

        viewModel.getCityForecast(weatherCity.name)
        val value=viewModel.cityforecast.getOrAwaitValueTest()
        assertThat(value.cod).isEqualTo(Cod.OK)
    }
    @Test
    fun `getForeCastDataFromApi,return error`(){
        repository.setShouldReturnError(true)

        val weatherCity=WeatherCity(44,"cairo", Coord(88.0,99.0),"egypt",
            1000,0,0,0)

        viewModel.getCityForecast(weatherCity.name)

        val value=viewModel.cityforecast.getOrAwaitValueTest()
        assertThat(value.cod).isEqualTo(Cod.ERROR)

    }
}
