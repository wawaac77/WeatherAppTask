package com.practice.weather.extension

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.practice.weather.delegate.ActivityBindingProperty
import com.practice.weather.delegate.ViewModelDelegate
import kotlin.reflect.KClass

fun <T : ViewModel> Fragment.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
  return ViewModelProviders.of(this, factory).get(model.java)
}

fun <T : ViewModel> FragmentActivity.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
  return ViewModelProviders.of(this, factory).get(model.java)
}

fun <T : ViewDataBinding> FragmentActivity.activityBinding(@LayoutRes resId: Int) = ActivityBindingProperty<T>(resId)

fun <T : ViewModel> FragmentActivity.vmDelegate(clazz: KClass<T>) = lazy { ViewModelDelegate(clazz) }

fun <T : ViewModel> FragmentActivity.viewModel(viewModelDelegate: ViewModelDelegate<T>, viewModelFactory: ViewModelProvider.Factory) = viewModelDelegate.createViewModel(this, viewModelFactory)
