package com.practice.weather.di

import com.practice.weather.view.ui.main.WeatherListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentModule {

  @ContributesAndroidInjector
  abstract fun contributeWeatherListFragment(): WeatherListFragment
}
