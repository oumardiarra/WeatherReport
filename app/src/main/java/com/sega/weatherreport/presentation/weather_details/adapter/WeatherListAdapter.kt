package com.sega.weatherreport.presentation.weather_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.WeatherItemBinding
import com.sega.weatherreport.databinding.WeatherItemHeaderBinding
import com.sega.weatherreport.domain.model.WeatherInfo
import timber.log.Timber

class WeatherListAdapter :
    ListAdapter<WeatherInfo, RecyclerView.ViewHolder>(WeatherListDiffCallback) {

    companion object WeatherListDiffCallback : DiffUtil.ItemCallback<WeatherInfo>() {
        override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        Timber.i("getItemViewType value is $position")
        return if (position == 0)
            R.layout.weather_item_header
        else
            R.layout.weather_item


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /* val view =
             WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
         return WeatherListViewHolder(view)*/
        return when (viewType) {
            R.layout.weather_item_header -> {
                val view =
                    WeatherItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                WeatherListHeaderViewHolder(view)
            }
            R.layout.weather_item -> {
                val view =
                    WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WeatherListViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
        /*return if (viewType == 0) {
            val view =
                WeatherItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherListImageViewHolder(view)
        } else {
            val view =
                WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherListViewHolder(view)
        }*/

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is WeatherListHeaderViewHolder -> holder.bind(item)
            is WeatherListViewHolder -> holder.bind(item)

        }


    }

}

class WeatherListViewHolder(val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WeatherInfo) {
        val weatherIconUrl = "https://openweathermap.org/img/w/${item.weather.get(0).icon}.png"
        item.weather.get(0).icon = weatherIconUrl
        binding.weatherInfo = item
        binding.executePendingBindings()
    }
}

class WeatherListHeaderViewHolder(val binding: WeatherItemHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WeatherInfo) {
        binding.weatherInfo = item
        binding.executePendingBindings()
    }
}
