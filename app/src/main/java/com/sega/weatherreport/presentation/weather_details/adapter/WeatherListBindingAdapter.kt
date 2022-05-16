package com.sega.weatherreport.presentation.weather_details.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import timber.log.Timber

@BindingAdapter("weatherImage")

fun fetchImage(view: ImageView, src: String?) {
    src?.let {
        val uri = src.toUri().buildUpon().scheme("https").build()

        Picasso.get().load(uri).resize(120, 120)
            .into(view)
    }
}

@BindingAdapter("shouldDisplayView")

fun displayView(view: View, ShouldDisplayView: Boolean) {
    Timber.e("shouldDisplayView ${ShouldDisplayView}")
    if(ShouldDisplayView){
        view.visibility=View.VISIBLE
    }
    else{
        view.visibility=View.INVISIBLE
    }

}