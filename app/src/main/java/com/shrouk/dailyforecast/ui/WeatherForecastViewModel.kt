package com.shrouk.dailyforecast.ui

import androidx.lifecycle.*
import com.shrouk.dailyforecast.model.Resource
import com.shrouk.dailyforecast.model.WeatherCity
import com.shrouk.dailyforecast.model.WeatherResponse
import com.shrouk.dailyforecast.model.WeatherList
import com.shrouk.dailyforecast.repositories.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(private var repository:ForecastRepository):ViewModel() {
    private var _cityforecast = MutableLiveData<Resource<ArrayList<WeatherList>, WeatherCity>>()
    var cityforecast: LiveData<Resource<ArrayList<WeatherList>,WeatherCity> > = _cityforecast
    var city = MediatorLiveData<WeatherResponse>()

// this function get forecast data from Api
    fun getCityForecast(city: String) {
        viewModelScope.launch {
            val forecast = repository.getCityForecast(city)
          //  Log.e("TAG", "getCityForecas: $forecast ")
          _cityforecast.value = forecast
        }

    }
//saving data in Room
    fun saveForecast (weatherResponse: WeatherResponse) {
        viewModelScope.launch {
             repository.saveForecast(weatherResponse)
        }
    }
// this function get forecast data from room
    fun getSavedForecast(cityName:String) {
        viewModelScope.launch {
           city.addSource( repository.getSavedForecast(cityName)){
               if(it!=null){
                   city.value=WeatherResponse(it.weatherList,it.weatherCity)

               }else{
                   city.value= WeatherResponse(listOf(),WeatherCity())
               }

           }
        }
    }
}