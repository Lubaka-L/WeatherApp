package com.example.weatherapptogit.ui.mainWeather.main

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapptogit.R
import com.example.weatherapptogit.core.ResultWrapper
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.databinding.FragmentMainWeatherBinding
import com.example.weatherapptogit.ui.mainWeather.adapters.DayRecyclerViewAdapter
import com.example.weatherapptogit.ui.mainWeather.adapters.HourRecyclerViewAdapter
import com.example.weatherapptogit.ui.search.SearchFragment
import com.example.weatherapptogit.ui.search.SearchFragment.Companion.OBSERVING_KEY_CITY
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class MainWeatherFragment : Fragment() {

    private lateinit var viewModel: MainWeatherViewModel
    private lateinit var binding: FragmentMainWeatherBinding

    private var dayRecyclerViewAdapter: DayRecyclerViewAdapter? = null
    private var hourRecyclerViewAdapter: HourRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainWeatherViewModel::class.java]

        dayRecyclerViewAdapter = DayRecyclerViewAdapter()
        binding.dayRV.adapter = dayRecyclerViewAdapter

        hourRecyclerViewAdapter = HourRecyclerViewAdapter()
        binding.hourRV.adapter = hourRecyclerViewAdapter

        lifecycleScope.launch {
            viewModel.weather.collectLatest { weatherResult ->
                when(weatherResult){
                    is ResultWrapper.Success -> {
                        binding.refreshLayout.isRefreshing = false
                        weatherResult.data.let { weatherResult ->

                            hourRecyclerViewAdapter?.submitList(weatherResult.second)

                            val formatter = DateTimeFormatter.ofPattern("HH:mm")

                            binding.apply {
                                currentWeatherIcon.setImageResource(weatherResult.first!!.image)
                                currentTemperature.text = "${weatherResult.first.temp} °C"
                                feelsTemperatureCount.text = " ${weatherResult.first.feels_like} °C"
                                currentState.text = weatherResult.first.weather_description
                                minTemp.text = "${weatherResult.first.temp_min} °C"
                                maxTemp.text = "${weatherResult.first.temp_max} °C"
                                windCountTitle.text = weatherResult.first.wind_speed.toString()
                                sunriseTime.text = weatherResult.first.sunrise?.format(formatter)
                                sunsetTime.text = weatherResult.first.sunset?.format(formatter)
                                humidityCount.text = "${weatherResult.first.humidity} %"
                                feelsCount.text = "${weatherResult.first.feels_like} °C"
                                cloudyCount.text = "${weatherResult.first.clouds} %"
                                pressureCount.text = "${weatherResult.first.pressure} мм"
                                visibilityCount.text = "${weatherResult.first.visibility} м"
                                airQualityCount.text = weatherResult.first.air_quality.toString()
                            }
                        }
                    }
                    is ResultWrapper.Load -> {
                        binding.refreshLayout.isRefreshing = true
                    }
                    is ResultWrapper.Error -> {
                        binding.refreshLayout.isRefreshing = false

                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.currentCity.collectLatest {
                binding.currentCity.text = it?.city
            }
        }

        lifecycleScope.launch {
            viewModel.airQuality.collectLatest {
                binding.airQualityCount.text = it?.toString()
            }
        }

        binding.searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainWeatherFragment_to_searchFragment)
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.update()
        }

        parentFragmentManager.setFragmentResultListener(
            SearchFragment.OBSERVING_KEY,
            viewLifecycleOwner
        ) { requestKey, result ->
            when (requestKey) {
                SearchFragment.OBSERVING_KEY -> {
                    val resultCity: Cities = result.get(OBSERVING_KEY_CITY) as Cities
                    binding.currentCity.text = resultCity.city
                    viewModel.loadWeather()
                }
            }
        }
    }


}