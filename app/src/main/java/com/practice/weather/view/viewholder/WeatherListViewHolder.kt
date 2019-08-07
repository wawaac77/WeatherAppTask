package com.practice.weather.view.viewholder

import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.practice.weather.models.entity.Weather
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherListViewHolder(view: View, private val delegate: Delegate)
  : BaseViewHolder(view) {

  interface Delegate {
    fun onItemClick(weather: Weather)
  }

  private lateinit var weather: Weather

  override fun bindData(data: Any) {
    if (data is Weather) {
      weather = data
      drawItem()
    }
  }

  private fun drawItem() {
    itemView.run {
      locationTextView.text = weather.name
      temperatureTextView.text = if (weather.weatherTemp != null) weather.weatherTemp + "°" else ""
      weatherTextView.text = weather.weatherCondition
    }
  }

  override fun onClick(v: View?) {
    delegate.onItemClick(weather)
  }

  override fun onLongClick(v: View?) = false
}
