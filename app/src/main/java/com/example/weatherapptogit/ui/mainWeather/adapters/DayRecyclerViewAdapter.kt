package com.example.weatherapptogit.ui.mainWeather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptogit.data.enums.DateFormat
import com.example.weatherapptogit.databinding.DayItemBinding
import com.example.weatherapptogit.domain.models.Weather

class DayRecyclerViewAdapter :
    ListAdapter<Weather, DayRecyclerViewAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(DayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hourWeather = getItem(position)
    }


    class ViewHolder(private var binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var hourWeather: Weather? = null
            set(value) {
                value?.let { newValue ->
                    field = newValue

                    binding.apply {
                        data.text = newValue.data
                        minTemp.text = newValue.temp_min.toString()
                        maxTemp.text = newValue.temp_max.toString()
                        dayIcon.setImageResource(newValue.image)
                        weatherType.text = newValue.weather_description
                    }

                }
            }


    }

    companion object diffUtil : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.data == newItem.data
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }
    }


