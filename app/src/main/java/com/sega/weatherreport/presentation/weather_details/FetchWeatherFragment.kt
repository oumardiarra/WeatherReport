package com.sega.weatherreport.presentation.weather_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.FragmentFetchWeatherBinding


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
        // Inflate the layout for this fragment
        binding.btnTest.setOnClickListener {
            viewModel.fectDate()
        }
        return root
    }


}