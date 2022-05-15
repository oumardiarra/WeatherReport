package com.sega.weatherreport.presentation.weather_details.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("weatherImage")

fun fetchImage(view: ImageView, src: String?) {
    src?.let {
        val uri = src.toUri().buildUpon().scheme("https").build()

        Picasso.get().load(uri).resize(120, 120)
            .into(view)
    }
}