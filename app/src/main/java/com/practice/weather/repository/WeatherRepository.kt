package com.practice.weather.repository

import androidx.lifecycle.LiveData
import com.practice.weather.api.ApiResponse
import com.practice.weather.api.WeatherService
import com.practice.weather.mappers.*
import com.practice.weather.models.Resource
import com.practice.weather.models.entity.Weather
import com.practice.weather.models.network.*
import com.practice.weather.room.WeatherDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject
constructor(val service: WeatherService, val weatherDao: WeatherDao)
  : Repository {

  init {
    Timber.d("Injection WeatherRepository")
  }

  fun loadWeatherList(id: Int): LiveData<Resource<List<Weather>>> {
    return object : NetworkBoundRepository<List<Weather>, WeatherListResponse, WeatherResponseMapper>() {
      override fun saveFetchData(items: WeatherListResponse) {
        weatherDao.insertWeatherList(weathers = items.data)
      }

      override fun shouldFetch(data: List<Weather>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Weather>> {
        return weatherDao.getWeatherList()
      }

      override fun fetchService(): LiveData<com.practice.weather.api.ApiResponse<WeatherListResponse>> {
        return service.fetchWeather()
      }

      override fun mapper(): WeatherResponseMapper {
        return WeatherResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }
}
