package com.practice.weather.mappers

import com.practice.weather.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
  fun onLastPage(response: FROM): Boolean
}
