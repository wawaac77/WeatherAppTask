package com.practice.weather.view.ui.main

import androidx.lifecycle.*
import com.practice.weather.models.Resource
import com.practice.weather.models.entity.Weather
import com.practice.weather.repository.WeatherRepository
import com.practice.weather.utils.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject
constructor(
  private val weatherRepository: WeatherRepository
) : ViewModel() {

  private var weatherPageLiveData: MutableLiveData<Int> = MutableLiveData()
  val weatherListLiveData: LiveData<Resource<List<Weather>>>

  init {
    Timber.d("injection MainActivityViewModel")

    weatherListLiveData = weatherPageLiveData.switchMap {
      weatherPageLiveData.value.let { weatherRepository.loadWeatherList(0) }
              ?: AbsentLiveData.create()
    }

  }

  fun getWeatherListValues() = weatherListLiveData.value
  fun postWeatherPage(page: Int) = weatherPageLiveData.postValue(page)

}
