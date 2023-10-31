package com.example.weatherapptogit.ui.mainWeather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptogit.databinding.HourItemBinding
import com.example.weatherapptogit.domain.models.Weather

class HourRecyclerViewAdapter : ListAdapter<Weather, HourRecyclerViewAdapter.ViewHolder>(diffUtil){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hourWeather = getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HourItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class ViewHolder(private var binding: HourItemBinding) : RecyclerView.ViewHolder(binding.root){
    var hourWeather : Weather? = null
        set(value) {
            value?.let { newValue ->
                field = newValue

                binding.apply {
                    currentWeatherIcon.setImageResource(newValue.image)
                    currentTemperature.text = newValue.temp.toString()
                    val date = (newValue.data).drop(11).dropLast(3)
                    time.text = date
                }

            }
        }


    }
    companion object diffUtul{
        var diffUtil = object : DiffUtil.ItemCallback<Weather>(){
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.data == newItem.data
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }
    }

}