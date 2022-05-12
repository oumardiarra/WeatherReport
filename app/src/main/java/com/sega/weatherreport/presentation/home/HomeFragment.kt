package com.sega.weatherreport.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnStart.setOnClickListener {

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFetchWeatherFragment())
        }
        // Inflate the layout for this fragment
        return view
    }


}