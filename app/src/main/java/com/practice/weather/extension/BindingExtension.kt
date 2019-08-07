package com.practice.weather.extension

import android.view.View
import com.practice.weather.models.Resource
import com.practice.weather.models.network.Status
import org.jetbrains.anko.toast

fun View.bindResource(resource: Resource<Any>?, onSuccess: () -> Unit) {
  if (resource != null) {
    when (resource.status) {
      Status.LOADING -> Unit
      Status.SUCCESS -> onSuccess()
      Status.ERROR -> this.context.toast(resource.errorEnvelope?.status_message.toString())
    }
  }
}
