package com.practice.weather.di

import androidx.annotation.NonNull
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.practice.weather.api.*
import com.practice.weather.api.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(com.practice.weather.api.RequestInterceptor())
      .addNetworkInterceptor(StethoInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://dnu5embx6omws.cloudfront.net/venues/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(com.practice.weather.api.LiveDataCallAdapterFactory())
            .build()
  }

  @Provides
  @Singleton
  fun provideWeatherService(@NonNull retrofit: Retrofit): WeatherService {
    return retrofit.create(WeatherService::class.java)
  }
}
