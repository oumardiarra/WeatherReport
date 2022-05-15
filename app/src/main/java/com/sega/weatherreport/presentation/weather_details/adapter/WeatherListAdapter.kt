package com.sega.weatherreport.presentation.weather_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sega.weatherreport.R
import com.sega.weatherreport.databinding.WeatherItemBinding
import com.sega.weatherreport.databinding.WeatherItemImageBinding
import com.sega.weatherreport.domain.model.WeatherInfo
import timber.log.Timber

class WeatherListAdapter :
    ListAdapter<WeatherInfo, WeatherListViewHolder>(WeatherListDiffCallback) {

    companion object WeatherListDiffCallback : DiffUtil.ItemCallback<WeatherInfo>() {
        override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }

    }

    /*  override fun getItemViewType(position: Int): Int {
          Timber.i("getItemViewType value is $position")
          return if (position == 0)
              R.layout.weather_item_image
          else
              R.layout.weather_item


      }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view =
            WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherListViewHolder(view)
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

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        /* if (holder.itemViewType == R.layout.weather_item_image) {
             (holder as WeatherListImageViewHolder).bind(item)
         } else {
             (holder as WeatherListViewHolder).bind(item)
         }*/

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

class WeatherListImageViewHolder(val binding: WeatherItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WeatherInfo) {
        binding.weatherInfo = item
        binding.executePendingBindings()
    }
}
