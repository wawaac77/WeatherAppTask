package com.practice.weather

import com.facebook.stetho.Stetho
import com.practice.weather.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

@Suppress("unused")
class WeatherApplication : DaggerApplication() {

  private val appComponent = com.practice.weather.di.DaggerAppComponent.builder()
    .application(this)
    .build()

  override fun onCreate() {
    super.onCreate()
    appComponent.inject(this)

    if (com.practice.weather.BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    Stetho.initializeWithDefaults(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return appComponent
  }
}
