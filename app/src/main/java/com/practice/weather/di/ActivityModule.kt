package com.practice.weather.di

import com.practice.weather.view.ui.details.weather.WeatherDetailActivity
import com.practice.weather.view.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
  @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
  internal abstract fun contributeMainActivity(): MainActivity

  @ContributesAndroidInjector
  internal abstract fun contributeWeatherDetailActivity(): WeatherDetailActivity
}
