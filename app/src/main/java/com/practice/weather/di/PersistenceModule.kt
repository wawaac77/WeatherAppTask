package com.practice.weather.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.practice.weather.room.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@NonNull application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "Weather.db").allowMainThreadQueries().build()
  }

  @Provides
  @Singleton
  fun provideWeatherDao(@NonNull database: AppDatabase): WeatherDao {
    return database.weatherDao()
  }
}
