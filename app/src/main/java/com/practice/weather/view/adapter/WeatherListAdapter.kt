package com.practice.weather.view.adapter

import android.view.View
import com.practice.weather.R
import com.practice.weather.models.Resource
import com.practice.weather.models.entity.Weather
import com.practice.weather.view.viewholder.WeatherListViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class WeatherListAdapter(private val delegate: WeatherListViewHolder.Delegate, private val type: Int)
  : BaseAdapter() {

  init {
    addSection(ArrayList<Weather>())
  }

  fun addWeatherList(resource: Resource<List<Weather>>) {
    resource.data?.let {
      if (type == 0) {
        sections()[0].addAll(it.sortedBy { it.name })
      } else if (type == 1) {
        sections()[0].addAll(it.sortedByDescending { it.weatherTemp?.toInt() })
      } else {
        sections()[0].addAll(it.sortedByDescending {it.weatherLastUpdated })
      }
      notifyDataSetChanged()
    }
  }

  override fun layout(sectionRow: SectionRow): Int {
    return R.layout.item_weather
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    return WeatherListViewHolder(view, delegate)
  }
}
