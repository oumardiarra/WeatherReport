package com.sega.weatherreport.presentation.weather_details

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.textview.MaterialTextView
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.FragmentFetchWeatherBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
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
        /*binding.btnRetry.setOnClickListener {
            binding.btnRetry.visibility = View.INVISIBLE
            binding.linearProgressIndicator.progress = 0
            binding.linearProgressIndicator.visibility = View.VISIBLE
            binding.waitingMessage.visibility = View.VISIBLE
            binding.waitingMessage.text = getString(R.string.waiting_msg_init)
            viewModel.fetchCurrentWeather()

        }*/
        val representativeAdapter = WeatherListAdapter()
        val recycleWeather = binding.recyclerWeather
        val divider = MaterialDividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL /*or LinearLayoutManager.HORIZONTAL*/
        )
        recycleWeather.adapter = representativeAdapter
        recycleWeather.addItemDecoration(divider)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.progressIndicator.collect {
                        binding.linearProgressIndicator.progress = it
                    }
                }
                launch {
                    viewModel.waitingMessage.collect {
                        Timber.i("Waiting message collect called $it")
                        if (it.isNotEmpty()) {
                            binding.waitingMessage.text = it
                        }
                    }
                }
                launch {
                    viewModel.listOfWeather.collect {

                        if (it.isNotEmpty()) {
                            representativeAdapter.submitList(it)
                        }


                    }
                }


            }
        }
        viewModel.fetchCurrentWeather()
        return root
    }


}