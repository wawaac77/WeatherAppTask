package com.practice.weather.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practice.weather.extension.bindResource
import com.practice.weather.models.Resource
import com.practice.weather.models.entity.Weather
import com.practice.weather.view.adapter.*

@BindingAdapter("adapterWeatherList")
fun bindAdapterWeatherList(view: RecyclerView, resource: Resource<List<Weather>>?) {
  view.bindResource(resource) {
    if (resource != null) {
      val adapter = view.adapter as? WeatherListAdapter
      adapter?.addWeatherList(resource)
    }
  }
}

