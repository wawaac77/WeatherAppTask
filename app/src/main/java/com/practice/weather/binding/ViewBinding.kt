package com.practice.weather.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.practice.weather.extension.bindResource
import com.practice.weather.extension.visible
import com.practice.weather.models.Resource

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
  view.bindResource(resource) {
    if (resource?.data?.isNotEmpty()!!) {
      view.visible()
    }
  }
}
