package com.practice.weather.mappers

import com.practice.weather.models.network.WeatherListResponse

class WeatherResponseMapper : NetworkResponseMapper<WeatherListResponse> {
  override fun onLastPage(response: WeatherListResponse): Boolean {
    return true
  }
}
