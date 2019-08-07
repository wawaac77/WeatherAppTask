package com.practice.weather.view.ui.details.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.practice.weather.models.Resource
import com.practice.weather.models.entity.Weather
import com.practice.weather.repository.WeatherRepository
import com.practice.weather.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class WeatherDetailViewModel @Inject
constructor(private val repository: WeatherRepository) : ViewModel() {

  private val weatherIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val weatherLiveData: LiveData<Resource<List<Weather>>>

  init {
    Timber.d("Injection WeatherDetailViewModel")

    this.weatherLiveData = weatherIdLiveData.switchMap {
      weatherIdLiveData.value?.let {
        repository.loadWeatherList(it)
      } ?: AbsentLiveData.create()
    }
  }

  fun postWeatherId(id: Int) = weatherIdLiveData.postValue(id)
}
