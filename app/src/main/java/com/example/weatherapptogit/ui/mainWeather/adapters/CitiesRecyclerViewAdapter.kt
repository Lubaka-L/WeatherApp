package com.example.weatherapptogit.ui.mainWeather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.databinding.FragmentSearchBinding
import com.example.weatherapptogit.databinding.SearchItemBinding

interface CitiesDelegate {
    fun onCityClicked(city: Cities)
}

class CitiesRecyclerViewAdapter(private val delegate: CitiesDelegate) :
    ListAdapter<Cities, CitiesRecyclerViewAdapter.ViewHolder>(
        diffUtil
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), delegate
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cities = getItem(position)
    }


    class ViewHolder(
        private var binding: SearchItemBinding,
        private val delegate: CitiesDelegate
    ) : RecyclerView.ViewHolder(binding.root) {

        var cities: Cities? = null
            set(value) {
                value?.let { newValue ->
                    field = newValue
                    binding.apply {
                        citiesName.text = newValue.city
                        root.setOnClickListener {
                            delegate.onCityClicked(newValue)
                        }
                    }
                }
            }
    }

    companion object {
        var diffUtil = object : DiffUtil.ItemCallback<Cities>() {
            override fun areItemsTheSame(oldItem: Cities, newItem: Cities): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Cities, newItem: Cities): Boolean {
                return oldItem == newItem
            }
        }
    }


}