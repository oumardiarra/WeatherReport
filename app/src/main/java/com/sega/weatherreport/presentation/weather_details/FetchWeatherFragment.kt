package com.sega.weatherreport.presentation.weather_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.FragmentFetchWeatherBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FetchWeatherFragment : Fragment() {

    var _binding: FragmentFetchWeatherBinding? = null
    val binding get() = _binding!!

    val viewModel: FetchWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFetchWeatherBinding.inflate(inflater, container, false)
        val root = binding.root

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.progressIndicator.collect {
                    binding.linearProgressIndicator.progress = it
                }
            }
        }

        // Inflate the layout for this fragment
        binding.btnTest.setOnClickListener {
            viewModel.fetchCurrentWeather()
        }
        return root
    }


}