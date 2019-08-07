package com.practice.weather.delegate

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

class ViewModelDelegate<T : ViewModel>(
  private val clazz: KClass<T>
) {

  fun createViewModel(
    activity: FragmentActivity,
    viewModelFactory: ViewModelProvider.Factory?
  ): T {
    return ViewModelProviders.of(activity, viewModelFactory).get(clazz.java)
  }
}
