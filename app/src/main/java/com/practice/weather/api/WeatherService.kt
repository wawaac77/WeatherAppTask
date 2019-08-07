package com.practice.weather.api

import androidx.lifecycle.LiveData
import com.practice.weather.models.network.WeatherListResponse
import retrofit2.http.GET

interface WeatherService {

  @GET("weather.json")
  fun fetchWeather(): LiveData<com.practice.weather.api.ApiResponse<WeatherListResponse>>

}
