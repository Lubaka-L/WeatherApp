package com.example.weatherapptogit.ui.search
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapptogit.R
import com.example.weatherapptogit.data.enums.Cities
import com.example.weatherapptogit.databinding.FragmentSearchBinding
import com.example.weatherapptogit.ui.mainWeather.adapters.CitiesDelegate
import com.example.weatherapptogit.ui.mainWeather.adapters.CitiesRecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), CitiesDelegate {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    private var cityRecyclerViewAdapter: CitiesRecyclerViewAdapter? = null

    companion object {
        val OBSERVING_KEY = "SearchFragmentKey"
        val OBSERVING_KEY_CITY = "SearchFragmentKeyCity"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_mainWeatherFragment)
        }

        cityRecyclerViewAdapter = CitiesRecyclerViewAdapter(this)
        binding.rv.adapter = cityRecyclerViewAdapter

        lifecycleScope.launch {
            viewModel.citiesList.collectLatest { cities ->
                cityRecyclerViewAdapter?.submitList(cities)
            }
        }

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.showCitiesNames(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.showCitiesNames(newText!!)
                return true
            }
        })

    }

    override fun onCityClicked(city: Cities) {
        parentFragmentManager.setFragmentResult(OBSERVING_KEY, bundleOf(
            OBSERVING_KEY_CITY to city
        ))
        viewModel.saveCity(city)
        findNavController().popBackStack()
    }
}