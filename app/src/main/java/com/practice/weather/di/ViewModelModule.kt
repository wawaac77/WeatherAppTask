package com.practice.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.weather.factory.AppViewModelFactory
import com.practice.weather.view.ui.details.weather.WeatherDetailViewModel
import com.practice.weather.view.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(WeatherDetailViewModel::class)
  internal abstract fun bindWeatherDetailViewModel(weatherDetailViewModel: WeatherDetailViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
