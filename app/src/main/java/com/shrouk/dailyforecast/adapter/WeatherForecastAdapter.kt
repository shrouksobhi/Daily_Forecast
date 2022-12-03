package com.shrouk.dailyforecast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shrouk.dailyforecast.R
import com.shrouk.dailyforecast.model.WeatherList

class WeatherForecastAdapter
    (var weatherList:ArrayList<WeatherList>)
    :RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    class ViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview){
        var weatherState=itemview.findViewById<TextView>(R.id.weatherState_textview)!!
        var weatherDescription=itemview.findViewById<TextView>(R.id.weather_description_textview)!!
        var minTemp=itemview.findViewById<TextView>(R.id.temp_min_textview)!!
        var maxTemp=itemview.findViewById<TextView>(R.id.temp_max_textview)!!
        var pressure=itemview.findViewById<TextView>(R.id.pressure_textvie)!!
        var date =itemview.findViewById<TextView>(R.id.date_textview)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.search_item_list,parent,false)
    return ViewHolder(view) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val _weatherList=weatherList[position]
            holder.weatherDescription.text= _weatherList.weather[0].description
            holder.maxTemp.text=_weatherList.main.temp_max.toString().plus("℃")
            holder.minTemp.text=_weatherList.main.temp_min.toString().plus("℃ / ")
            holder.weatherState.text=_weatherList.weather[0].main
            holder.date.text=_weatherList.date
            holder.pressure.text=_weatherList.main.pressure.toString()


    }

    override fun getItemCount(): Int {
    return weatherList.size   }
}