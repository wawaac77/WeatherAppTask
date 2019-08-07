package com.practice.weather.models.network

import com.practice.weather.models.NetworkResponseModel
import com.practice.weather.models.entity.Weather

data class WeatherListResponse(
  val ret: Boolean,
  val isOkay: Boolean,
  val data: MutableList<Weather>
) : NetworkResponseModel
