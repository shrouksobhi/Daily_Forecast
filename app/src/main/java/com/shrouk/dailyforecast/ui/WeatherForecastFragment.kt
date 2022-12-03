package com.shrouk.dailyforecast.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.shrouk.dailyforecast.R
import com.shrouk.dailyforecast.adapter.WeatherForecastAdapter
import com.shrouk.dailyforecast.databinding.FragmentWeatherForecastBinding
import com.shrouk.dailyforecast.forecastExt.makeGone
import com.shrouk.dailyforecast.forecastExt.makeVisible
import com.shrouk.dailyforecast.forecastExt.observeOnce
import com.shrouk.dailyforecast.model.Cod
import com.shrouk.dailyforecast.model.WeatherList
import com.shrouk.dailyforecast.model.WeatherResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {
    private lateinit var binding: FragmentWeatherForecastBinding
    private lateinit var weatherForecastAdapter: WeatherForecastAdapter
    private val weatherForecastViewModel: WeatherForecastViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_forecast, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerview
        skeleton=binding.recyclerview.applySkeleton(R.layout.search_item_list)
        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                val query = binding.search.text.toString()
                if (query.isEmpty()) {
                    binding.recyclerview.makeGone()
                    Toast.makeText(requireContext(), "Enter city name ", Toast.LENGTH_SHORT).show()
                } else {
                    binding.recyclerview.makeVisible()

                    weatherForecastViewModel.getCityForecast(query)
                    observeResponse()

                    try {

                        val imm: InputMethodManager =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
                    } catch (e: Exception) {
                        Log.e("TAG", "${e.localizedMessage} ")
                    }
                }
            }
            return@setOnEditorActionListener false
        }

        binding.retry.setOnClickListener {
            binding.bottomConstrain.makeGone()
            skeleton=binding.recyclerview.applySkeleton(R.layout.search_item_list)
            val query = binding.search.text.toString()
            weatherForecastViewModel.getCityForecast(query)
            observeResponse()
        }
    }

    private fun observeResponse() {

        weatherForecastViewModel.cityforecast.observe(viewLifecycleOwner) {
            when (it.cod) {
                Cod.OK -> {
                    binding.notAccurate.makeGone()
                    binding.recyclerview.makeVisible()
                    skeleton.showSkeleton()

                    val datalist = it.weatherlist
                    installviews(datalist!!)
                    val weatherlist = it.weatherlist
                    val weathercity = it.weathercity
                    val weatherResponse = WeatherResponse(weatherlist, weathercity!!)
                    weatherForecastViewModel.saveForecast(weatherResponse)


                }
                Cod.ERROR -> {
                    binding.recyclerview.makeVisible()
                    val searchedCity = binding.search.text.toString()
                    weatherForecastViewModel.getSavedForecast(searchedCity)
                    observeRoomResponse()
                    // Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                Cod.LOADING ->
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.city_forecast_loading_state),
                        Toast.LENGTH_SHORT
                    ).show()

            }
        }
    }

    private fun observeRoomResponse() {
        //  weatherForecastViewModel.city.remove()
        weatherForecastViewModel.city.observeOnce(viewLifecycleOwner) {
            skeleton.showSkeleton()

            val list = (it.weatherList)
            //   Log.e("TAG", "observeRoomResponse: $list")
            if (list.isNotEmpty()) {
                binding.notAccurate.makeVisible()
                binding.bottomConstrain.makeGone()
                binding.recyclerview.makeVisible()
                installviews(java.util.ArrayList(list))
            } else {
                binding.notAccurate.makeGone()
                binding.recyclerview.makeGone()
                binding.bottomConstrain.makeVisible()
            }
        }
    }

    private fun installviews(weatherlist: ArrayList<WeatherList>) {
        skeleton.showOriginal()
        val layoutManager: LayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        weatherForecastAdapter = WeatherForecastAdapter(weatherlist)
        recyclerView.adapter = weatherForecastAdapter

    }


}