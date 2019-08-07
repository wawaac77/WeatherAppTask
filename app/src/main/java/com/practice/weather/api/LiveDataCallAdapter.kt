package com.practice.weather.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type)
  : CallAdapter<R, LiveData<com.practice.weather.api.ApiResponse<R>>> {

  override fun responseType(): Type {
    return responseType
  }

  override fun adapt(call: Call<R>): LiveData<com.practice.weather.api.ApiResponse<R>> {
    return object : LiveData<com.practice.weather.api.ApiResponse<R>>() {
      var started = AtomicBoolean(false)
      override fun onActive() {
        super.onActive()
        if (started.compareAndSet(false, true)) {
          call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
              postValue(com.practice.weather.api.ApiResponse(response))
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
              postValue(com.practice.weather.api.ApiResponse(throwable))
            }
          })
        }
      }
    }
  }
}
